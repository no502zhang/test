package com.wiseyq.face.service;

import java.util.Date;
import java.util.List;

import com.wiseyq.face.model.VisitorCount;

public interface VisitorCountService {
    List<VisitorCount> findDailyVisitorCount(String cameraId, Date beginTime, Date endTime);

    List<VisitorCount> findTimeVisitorCount(String cameraId, Date beginTime, Date endTime);
}
