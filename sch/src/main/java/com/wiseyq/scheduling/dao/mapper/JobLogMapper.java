package com.wiseyq.scheduling.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wiseyq.scheduling.model.JobLog;

@Mapper
public interface JobLogMapper {
    int insertJobLog(JobLog jobLog);

    int updateJobLog(JobLog jobLog);
    
    JobLog getJobLog(@Param("id") long id);

    List<JobLog> listJobLog(JobLog jobLog);
}
