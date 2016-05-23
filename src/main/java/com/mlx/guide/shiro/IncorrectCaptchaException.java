package com.mlx.guide.shiro;

import org.apache.shiro.authc.AuthenticationException;

/**
 * 验证码验证异常
 * @author quan
 *
 */
@SuppressWarnings("serial")
public class IncorrectCaptchaException extends AuthenticationException {

	public IncorrectCaptchaException() {
		super();
	}

	public IncorrectCaptchaException(String message, Throwable cause) {
		super(message, cause);
	}

	public IncorrectCaptchaException(String message) {
		super(message);
	}

	public IncorrectCaptchaException(Throwable cause) {
		super(cause);
	}
	
}
