package com.wiseyq.scheduling.service;

import org.quartz.SchedulerException;

import com.github.pagehelper.PageInfo;
import com.wiseyq.scheduling.model.JobInfo;

public interface JobService {
    JobInfo createJob(JobInfo jobInfo) throws SchedulerException;

    JobInfo updateJob(String id, JobInfo jobInfo);

    Boolean deleteJob(String id) throws SchedulerException;

    JobInfo getJob(String id) throws SchedulerException;

    PageInfo<JobInfo> listJobs(JobInfo jobInfo, int pageNum, int pageSize);
}