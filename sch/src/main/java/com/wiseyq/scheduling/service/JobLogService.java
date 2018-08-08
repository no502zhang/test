package com.wiseyq.scheduling.service;

import com.github.pagehelper.PageInfo;
import com.wiseyq.scheduling.model.JobLog;

public interface JobLogService {
    PageInfo<JobLog> listJobLogs(JobLog jobLog, int pageNum, int pageSize);
}
