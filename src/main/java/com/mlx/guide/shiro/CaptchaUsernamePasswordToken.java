package com.mlx.guide.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

import com.mlx.guide.constant.EUserType;

/**
 * 验证码
 * @author quan
 *
 */
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String captcha;
	
	private String accessToken;
	
	private EUserType userType;
	
	public CaptchaUsernamePasswordToken(String username, char[] password) {
		super(username, password);
	}
	
	public CaptchaUsernamePasswordToken(String username, char[] password,boolean rememberMe) {
		super(username, password, rememberMe);
	}
	
	public CaptchaUsernamePasswordToken(String username, char[] password,boolean rememberMe, String host) {
		super(username, password, rememberMe, host);
	}
	
	public CaptchaUsernamePasswordToken(String username, char[] password,boolean rememberMe, String host, String captcha) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
	}
	
	public CaptchaUsernamePasswordToken(String username, char[] password,boolean rememberMe, String host, String captcha, String accessToken) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
		this.accessToken = accessToken;
	}


	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public EUserType getUserType() {
		return userType;
	}

	public void setUserType( EUserType userType ) {
		this.userType = userType;
	}
	
}
