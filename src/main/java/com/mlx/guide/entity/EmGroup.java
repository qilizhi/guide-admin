package com.mlx.guide.entity;

import java.io.Serializable;
import java.util.Date;

import com.mlx.guide.constant.Const;

public class EmGroup implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String userNo;

    private String enterRule;

    private Integer type;

    private String ownerSign;

    private String ownerImage;

    private Long emGid;

    private String emGuser;

    private String emGname;

    private String emGdesc;

    private String image;

    private Integer emAffiliationsCount;

    private Integer emMaxusers;

    private Byte emPublic;

    private Byte emAllowinvites;

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

    public String getEnterRule() {
        return enterRule;
    }

    public void setEnterRule(String enterRule) {
        this.enterRule = enterRule;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOwnerSign() {
        return ownerSign;
    }

    public void setOwnerSign(String ownerSign) {
        this.ownerSign = ownerSign;
    }

    public String getOwnerImage() {
        return ownerImage;
    }

    public void setOwnerImage(String ownerImage) {
        this.ownerImage = ownerImage;
    }

    public Long getEmGid() {
        return emGid;
    }

    public void setEmGid(Long emGid) {
        this.emGid = emGid;
    }

    public String getEmGuser() {
        return emGuser;
    }

    public void setEmGuser(String emGuser) {
        this.emGuser = emGuser;
    }

    public String getEmGname() {
        return emGname;
    }

    public void setEmGname(String emGname) {
        this.emGname = emGname;
    }

    public String getEmGdesc() {
        return emGdesc;
    }

    public void setEmGdesc(String emGdesc) {
        this.emGdesc = emGdesc;
    }

    public String getImage() {
    	if(image==null||image.equals(" ")||image.startsWith("http://")){
    		return image;
    	}
    	
        return Const.IMAGEPREFIX+image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getEmAffiliationsCount() {
        return emAffiliationsCount;
    }

    public void setEmAffiliationsCount(Integer emAffiliationsCount) {
        this.emAffiliationsCount = emAffiliationsCount;
    }

    public Integer getEmMaxusers() {
        return emMaxusers;
    }

    public void setEmMaxusers(Integer emMaxusers) {
        this.emMaxusers = emMaxusers;
    }

    public Byte getEmPublic() {
        return emPublic;
    }

    public void setEmPublic(Byte emPublic) {
        this.emPublic = emPublic;
    }

    public Byte getEmAllowinvites() {
        return emAllowinvites;
    }

    public void setEmAllowinvites(Byte emAllowinvites) {
        this.emAllowinvites = emAllowinvites;
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