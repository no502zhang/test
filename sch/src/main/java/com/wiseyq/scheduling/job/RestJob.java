package com.wiseyq.scheduling.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.wiseyq.scheduling.dao.mapper.JobLogMapper;
import com.wiseyq.scheduling.model.JobInfo;
import com.wiseyq.scheduling.model.JobLog;

@Component
@EnableScheduling
public class RestJob implements Job {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private JobLogMapper jobLogMapper;

    @Override
    public void execute(JobExecutionContext context) {
        JobInfo jobInfo = (JobInfo) context.getJobDetail().getJobDataMap().get("jobInfo");
        System.out.println("开始任务[" + jobInfo.getName() + "]");

        // 请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        // 请求体
        HttpEntity<String> jsonStringEntity = new HttpEntity<String>(jobInfo.getParamStr(), headers);
        String url = "http://" + jobInfo.getSystem() + jobInfo.getUrl();

        JobLog jobLog = new JobLog();
        jobLog.setJobId(jobInfo.getId());
        jobLog.setMothed("POST");
        jobLog.setUrl(url);
        jobLog.setRequest(JSON.toJSONString(jsonStringEntity));
        jobLog.setBeginTime(new Date());
        jobLogMapper.insertJobLog(jobLog);

        String response = null;
        try {
            ResponseEntity<Object> result = restTemplate.postForEntity(url, jsonStringEntity, Object.class);
            response = JSON.toJSONString(result);
        } catch (Exception e) {
            response = e.getMessage();
        } finally {
            jobLog.setResponse(response);
            jobLog.setEndTime(new Date());
            jobLogMapper.updateJobLog(jobLog);
        }
    }
}