package com.wiseyq.scheduling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.wiseyq.scheduling.model.JobLog;
import com.wiseyq.scheduling.service.JobLogService;

@RestController
@RequestMapping("/scheduling/jobLogs")
public class JobLogController {

    @Autowired
    private JobLogService jobLogService;

    @GetMapping("/list")
    public ResponseEntity<PageInfo<JobLog>> list(JobLog jobLog,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        PageInfo<JobLog> result = jobLogService.listJobLogs(jobLog, pageNum, pageSize);
        return ResponseEntity.ok().body(result);
    }
}
