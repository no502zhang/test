package com.wiseyq.scheduling.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wiseyq.scheduling.dao.mapper.JobLogMapper;
import com.wiseyq.scheduling.model.JobLog;
import com.wiseyq.scheduling.service.JobLogService;

@Service
public class JobLogServiceImpl implements JobLogService {

    @Autowired
    private JobLogMapper jobLogMapper;

    @Override
    public PageInfo<JobLog> listJobLogs(JobLog jobLog, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<JobLog> jobLogList = jobLogMapper.listJobLog(jobLog);
        return new PageInfo<JobLog>(jobLogList);
    }
}
