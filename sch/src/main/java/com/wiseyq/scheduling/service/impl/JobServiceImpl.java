package com.wiseyq.scheduling.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wiseyq.scheduling.dao.mapper.JobInfoMapper;
import com.wiseyq.scheduling.job.RestJob;
import com.wiseyq.scheduling.model.JobInfo;
import com.wiseyq.scheduling.service.JobService;

import java.util.List;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private Scheduler scheduler;
    @Autowired
    private JobInfoMapper jobInfoMapper;

    private String jobNamePrefix = "job";
    private String defaultJobGroup = "defaultGroup";

    private String triggerNamePrefix = "trigger";
    private String defaultTriggerGroup = "defaultGroup";

    @Override
    public JobInfo createJob(JobInfo jobInfo) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(RestJob.class)
                .withIdentity(jobNamePrefix + "#" + jobInfo.getId(), defaultJobGroup)
                .withDescription(jobInfo.getDescription()).build();
        // 运行参数
        jobDetail.getJobDataMap().put("jobInfo", jobInfo);

        ScheduleBuilder<CronTrigger> scheduleBuilder = CronScheduleBuilder.cronSchedule(jobInfo.getCron());
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerNamePrefix + "#" + jobInfo.getId(), defaultTriggerGroup)
                .withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, trigger);

        return jobInfo;
    }

    @Override
    public JobInfo updateJob(String id, JobInfo jobInfo) {
        // TODO 查询记录
        return null;
    }

    @Override
    public Boolean deleteJob(String id) throws SchedulerException {
        deleteSchedulerJob(id);
        return true;
    }

    @Override
    public JobInfo getJob(String id) throws SchedulerException {
        JobKey key = JobKey.jobKey(jobNamePrefix + "#" + id, defaultJobGroup);
        return (JobInfo) scheduler.getJobDetail(key).getJobDataMap().get("jobInfo");
    }

    @Override
    public PageInfo<JobInfo> listJobs(JobInfo jobInfo, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<JobInfo> jobList = jobInfoMapper.listJobInfo(jobInfo);
        return new PageInfo<JobInfo>(jobList);
    }

    private Boolean deleteSchedulerJob(String id) throws SchedulerException {
        JobKey key = JobKey.jobKey(jobNamePrefix + "#" + id, defaultJobGroup);
        scheduler.deleteJob(key);
        return true;
    }
}