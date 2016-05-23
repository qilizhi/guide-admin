package com.mlx.guide.entity;

import java.io.Serializable;
import java.util.Date;

public class EmGroupUser implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String userNo;

    private Long emGid;

    private String emUser;

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

    public Long getEmGid() {
        return emGid;
    }

    public void setEmGid(Long emGid) {
        this.emGid = emGid;
    }

    public String getEmUser() {
        return emUser;
    }

    public void setEmUser(String emUser) {
        this.emUser = emUser;
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