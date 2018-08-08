package com.wiseyq.scheduling.controller;

import com.github.pagehelper.PageInfo;
import com.wiseyq.scheduling.model.JobInfo;
import com.wiseyq.scheduling.service.JobService;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scheduling/jobs")
public class JobController {
    @Autowired
    private JobService jobService;

    @PostMapping("/add")
    public ResponseEntity<JobInfo> create(@RequestBody JobInfo jobInfo) throws SchedulerException {
        jobService.createJob(jobInfo);
        return ResponseEntity.ok().body(jobInfo);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) throws SchedulerException {
        jobService.deleteJob(id);
        return ResponseEntity.ok().body(true);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") String id, @RequestBody JobInfo jobInfo) {
        return ResponseEntity.ok().body(false);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<JobInfo> get(@PathVariable("id") String id) {
        JobInfo job = new JobInfo();
        return ResponseEntity.ok().body(job);
    }

    @GetMapping("/list")
    public ResponseEntity<PageInfo<JobInfo>> list(JobInfo jobInfo,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        PageInfo<JobInfo> result = jobService.listJobs(jobInfo, pageNum, pageSize);
        return ResponseEntity.ok().body(result);
    }
}