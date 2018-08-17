package com.wiseyq.face.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huaweicloud.dis.DIS;
import com.huaweicloud.dis.DISClientBuilder;
import com.huaweicloud.dis.exception.DISClientException;
import com.huaweicloud.dis.iface.data.request.GetPartitionCursorRequest;
import com.huaweicloud.dis.iface.data.request.GetRecordsRequest;
import com.huaweicloud.dis.iface.data.response.GetPartitionCursorResult;
import com.huaweicloud.dis.iface.data.response.GetRecordsResult;
import com.huaweicloud.dis.iface.data.response.Record;
import com.huaweicloud.dis.iface.stream.request.DescribeStreamRequest;
import com.huaweicloud.dis.iface.stream.response.DescribeStreamResult;
import com.huaweicloud.dis.iface.stream.response.PartitionResult;
import com.huaweicloud.dis.util.JsonUtils;
import com.huaweicloud.dis.util.PartitionCursorTypeEnum;
import com.wiseyq.face.mapper.VisitorCountMapper;
import com.wiseyq.face.model.VisitorCount;

public class VisitorConutConsumer implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(VisitorConutConsumer.class);

    @Autowired
    private VisitorCountMapper visitorCountMapper;

    @Value("${dis.endpoint}")
    private String endpoint;
    @Value("${dis.region}")
    private String region;
    @Value("${dis.projectId}")
    private String projectId;
    @Value("${dis.streamName}")
    private String streamName;

    @Value("${huawei.ak}")
    private String ak;
    @Value("${huawei.sk}")
    private String sk;

    @PostConstruct
    public void init() {
        System.out.println("begin");
        Thread thread = new Thread(this, "visitorConutConsumer");
        System.out.println("start");
        thread.start();
        System.out.println("end");
    }

    @Override
    public void run() {
        // 创建DIS客户端实例
        DIS dic = DISClientBuilder.standard().withEndpoint(endpoint).withRegion(region).withProjectId(projectId)
                .withAk(ak).withSk(sk).build();
        // 取DIS信息
        DescribeStreamRequest describeStreamRequest = new DescribeStreamRequest();
        describeStreamRequest.setStreamName(streamName);
        DescribeStreamResult result = dic.describeStream(describeStreamRequest);
        System.out.println("descStream: " + JsonUtils.objToJson(result));

        List<PartitionResult> list = result.getPartitions();
        String startNum = "";
        if (CollectionUtils.isNotEmpty(list)) {
            startNum = list.get(0).getSequenceNumberRange().substring(1,
                    list.get(0).getSequenceNumberRange().indexOf(":"));
        }

        // 配置数据下载分区ID
        String partitionId = "shardId-0000000000";

        // 配置下载数据序列号
        long startingSequenceNumber = visitorCountMapper.getMaxId();
        if (startingSequenceNumber < NumberUtils.toLong(startNum.trim())) {
            startingSequenceNumber = NumberUtils.toLong(startNum.trim());
        }

        // 配置下载数据方式
        String cursorType = PartitionCursorTypeEnum.AT_SEQUENCE_NUMBER.name();

        // 获取数据游标
        GetPartitionCursorRequest request = new GetPartitionCursorRequest();
        request.setStreamName(streamName);
        request.setPartitionId(partitionId);
        request.setStartingSequenceNumber(String.valueOf(startingSequenceNumber));
        request.setCursorType(cursorType);
        GetPartitionCursorResult response = dic.getPartitionCursor(request);
        String cursor = response.getPartitionCursor();

        log.info("Get stream {}[partitionId={}] StartingSequenceNumber : {} cursor success : {}", streamName, partitionId, startingSequenceNumber, cursor);

        GetRecordsRequest recordsRequest = new GetRecordsRequest();
        GetRecordsResult recordResponse = null;
        while (true) {
            try {
                recordsRequest.setPartitionCursor(cursor);
                recordsRequest.setLimit(2);
                recordResponse = dic.getRecords(recordsRequest);
                // 下一批数据游标
                cursor = recordResponse.getNextPartitionCursor();

                for (Record record : recordResponse.getRecords()) {
//                    log.info("Get Record [{}], partitionKey [{}], sequenceNumber [{}].",
//                            new String(record.getData().array()), record.getPartitionKey(), record.getSequenceNumber());
                    JSONObject recordJSON = JSON.parseObject(new String(record.getData().array()));
                    VisitorCount vc = new VisitorCount();
                    vc.setSequenceNumber(record.getSequenceNumber());
                    vc.setCameraId(recordJSON.getString("camera_id"));
                    vc.setCreateTime(new Date(recordJSON.getLong("time") * 1000));
                    vc.setPersonCount(recordJSON.getInteger("person_count"));
                    vc.setStreamId("5bqtarswJprYrQBwpbB");
                    vc.setPartitionId(partitionId);
                    visitorCountMapper.insertVisitorCount(vc);
                }
                Thread.sleep(1000);
            } catch (DISClientException e) {
                log.error("Failed to get a normal response, please check params and retry. Error message [{}]",
                        e.getMessage(), e);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
