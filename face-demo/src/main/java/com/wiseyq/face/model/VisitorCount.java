package com.wiseyq.face.model;

import java.util.Date;

public class VisitorCount {
    private Long id;

    private String cameraId;
    private Integer personCount;
    private Date createTime;

    private String countDateStr;
    private String countHourStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCameraId() {
        return cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }

    public Integer getPersonCount() {
        return personCount;
    }

    public void setPersonCount(Integer personCount) {
        this.personCount = personCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCountDateStr() {
        return countDateStr;
    }

    public void setCountDateStr(String countDateStr) {
        this.countDateStr = countDateStr;
    }

    public String getCountHourStr() {
        return countHourStr;
    }

    public void setCountHourStr(String countHourStr) {
        this.countHourStr = countHourStr;
    }

}
