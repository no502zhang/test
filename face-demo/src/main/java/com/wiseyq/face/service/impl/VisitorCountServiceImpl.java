package com.wiseyq.face.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiseyq.face.mapper.VisitorCountMapper;
import com.wiseyq.face.model.VisitorCount;
import com.wiseyq.face.service.VisitorCountService;

@Service
public class VisitorCountServiceImpl implements VisitorCountService {
    @Autowired
    private VisitorCountMapper visitorCountMapper;

    @Override
    public List<VisitorCount> findDailyVisitorCount(String cameraId, Date beginTime, Date endTime) {
        return visitorCountMapper.findDailyVisitorCount(cameraId, beginTime, endTime);
    }

    @Override
    public List<VisitorCount> findTimeVisitorCount(String cameraId, Date beginTime, Date endTime) {
        return visitorCountMapper.findTimeVisitorCount(cameraId, beginTime, endTime);
    }

}
