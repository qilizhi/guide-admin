package com.mlx.guide.model;


/**
 * 平台方
 * 
 * @author quan
 * 
 */
public class PlatformUser {

	private Integer id;

	/**
	 * 登录帐号
	 */
	private String loginName;

	/**
	 * 用户编号，唯一
	 */
	private String userNo;

	/**
	 * 名字
	 */
	private String name;

	private String email;

	private String mobile;

	private String department;

	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName( String loginName ) {
		this.loginName = loginName;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo( String userNo ) {
		this.userNo = userNo;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile( String mobile ) {
		this.mobile = mobile;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment( String department ) {
		this.department = department;
	}

}