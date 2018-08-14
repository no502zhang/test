package com.wiseyq.face.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.wiseyq.face.model.FaceInfo;

@Mapper
public interface FaceInfoMapper {
    long insertFaceInfo(FaceInfo faceInfo);
    
    long getMaxId();
}
