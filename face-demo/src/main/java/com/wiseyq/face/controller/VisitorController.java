package com.wiseyq.face.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wiseyq.face.model.VisitorCount;
import com.wiseyq.face.service.VisitorCountService;

@RestController
@RequestMapping("/visitors")
public class VisitorController {

    @Autowired
    private VisitorCountService visitorCountService;

    @GetMapping("/dailyCount/list")
    public ResponseEntity<List<VisitorCount>> listDailyVisitorCount(String cameraId, Date beginTime, Date endTime) {
        return ResponseEntity.ok().body(visitorCountService.findDailyVisitorCount(cameraId, beginTime, endTime));
    }

    @GetMapping("/timeCount/list")
    public ResponseEntity<List<VisitorCount>> listTimeVisitorCount(String cameraId, Date beginTime, Date endTime) {
        return ResponseEntity.ok().body(visitorCountService.findTimeVisitorCount(cameraId, beginTime, endTime));
    }
}
