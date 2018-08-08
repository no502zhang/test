package com.wiseyq.scheduling.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wiseyq.scheduling.model.JobInfo;

@Mapper
public interface JobInfoMapper {
    int insertJobInfo(JobInfo jobInfo);

    int updateJobInfo(JobInfo jobInfo);

    int deleteJobInfo(@Param("id") int id);

    JobInfo getJobInfo(@Param("id") int id);

    List<JobInfo> listJobInfo(JobInfo jobInfo);
}
