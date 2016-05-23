package com.mlx.guide.entity;

import java.io.Serializable;
import java.util.Date;

public class EmUser implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String userNo;

    private String emType;

    private String emUser;

    private String emUuid;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getEmType() {
        return emType;
    }

    public void setEmType(String emType) {
        this.emType = emType;
    }

    public String getEmUser() {
        return emUser;
    }

    public void setEmUser(String emUser) {
        this.emUser = emUser;
    }

    public String getEmUuid() {
        return emUuid;
    }

    public void setEmUuid(String emUuid) {
        this.emUuid = emUuid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}