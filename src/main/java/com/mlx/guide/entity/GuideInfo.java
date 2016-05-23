package com.mlx.guide.entity;

import java.util.Date;

public class GuideInfo {
    private Long id;

    private String userNo;

    private Date updateTime;

    private Date createTime;

    private String realName;

    private String guideLevel;

    private String bgImgUrl;

    private Integer age;

    private String sign;

    private String tag;

    private Integer workYear;

    private String intro;

    private String guideCardNo;

    private String idCard;

    private String idCardFrontPic;

    private String idCardSidePic;

    private Integer auditStatus;

    private Integer status;

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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getGuideLevel() {
        return guideLevel;
    }

    public void setGuideLevel(String guideLevel) {
        this.guideLevel = guideLevel;
    }

    public String getBgImgUrl() {
        return bgImgUrl;
    }

    public void setBgImgUrl(String bgImgUrl) {
        this.bgImgUrl = bgImgUrl;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getWorkYear() {
        return workYear;
    }

    public void setWorkYear(Integer workYear) {
        this.workYear = workYear;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getGuideCardNo() {
        return guideCardNo;
    }

    public void setGuideCardNo(String guideCardNo) {
        this.guideCardNo = guideCardNo;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCardFrontPic() {
        return idCardFrontPic;
    }

    public void setIdCardFrontPic(String idCardFrontPic) {
        this.idCardFrontPic = idCardFrontPic;
    }

    public String getIdCardSidePic() {
        return idCardSidePic;
    }

    public void setIdCardSidePic(String idCardSidePic) {
        this.idCardSidePic = idCardSidePic;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}