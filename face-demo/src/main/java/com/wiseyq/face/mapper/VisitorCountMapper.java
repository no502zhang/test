package com.wiseyq.face.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wiseyq.face.model.VisitorCount;

@Mapper
public interface VisitorCountMapper {
    int insertVisitorCount(VisitorCount record);

    List<VisitorCount> findDailyVisitorCount(@Param("cameraId") String cameraId, @Param("beginTime") Date beginTime,
            @Param("endTime") Date endTime);

    List<VisitorCount> findTimeVisitorCount(@Param("cameraId") String cameraId, @Param("beginTime") Date beginTime,
            @Param("endTime") Date endTime);
    
    long getMaxId();
}
