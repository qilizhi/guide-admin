package com.mlx.guide.model;

import com.mlx.guide.entity.GuideInfo;


public class GuideInfoModel extends GuideInfo {
    private String startTime;
    
    private String endTime;

    private String isAuditStatus;
    
    
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getIsAuditStatus() {
		return isAuditStatus;
	}

	public void setIsAuditStatus(String isAuditStatus) {
		this.isAuditStatus = isAuditStatus;
	}
    
    
    
    
    
}