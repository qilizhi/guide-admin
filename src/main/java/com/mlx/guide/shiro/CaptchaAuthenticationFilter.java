package com.mlx.guide.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 验证码登录认证Filter(当用户登录失败次数达到指标时，才出现验证码。)
 * @author quan
 *
 */
@Component
public class CaptchaAuthenticationFilter extends FormAuthenticationFilter {

	private static Logger logger = LoggerFactory.getLogger(CaptchaAuthenticationFilter.class);
	/**
	 * 默认在session中存储的登录错误次数的名称
	 */
	public static final String ERROR_NUM = "incorrectNumber";

	// 验证码参数名称
	private String captchaParam = "CaptchaA";
	// Token参数名称
	private String tokenParam = "token_CaptchaA";
	// 在session中的存储验证码的key名称
	private String sessionCaptchaKeyAttribute = "sessionCaptchaKeyAttribute";
	// 在session中存储的登录错误次数名称
	private String loginIncorrectNumberKeyAttribute = ERROR_NUM;
	// 允许登录错误次数，当登录次数大于该数值时，会在页面中显示验证码
	private Integer allowIncorrectNumber = 3;

	/**
	 * 重写父类方法，在shiro执行登录时先对比验证码，正确后在登录，否则直接登录失败
	 */
	@Override
	protected boolean executeLogin(ServletRequest request,
			ServletResponse response) throws Exception {

		Session session = createSessionIfNull();
		// 获取登录错误次数
		Integer number = (Integer) session
				.getAttribute(getLoginIncorrectNumberKeyAttribute());

		// 首次登录，将该数量记录在session中
		if (number == null) {
			number = new Integer(1);
			session.setAttribute(getLoginIncorrectNumberKeyAttribute(), number);
		}

		// 如果登录次数大于allowIncorrectNumber，需要判断验证码是否一致
		if (number > getAllowIncorrectNumber()) {
			// 获取当前验证码
			String currentCaptcha = (String) session
					.getAttribute(getSessionCaptchaKeyAttribute());
			// 获取用户输入的验证码
			String submitCaptcha = getCaptcha(request);
			// 如果验证码不匹配，登录失败
			if (StringUtils.isEmpty(submitCaptcha)
					|| !currentCaptcha.equals(submitCaptcha.toLowerCase())) {
				return onLoginFailure(this.createToken(request, response),
						new IncorrectCaptchaException("验证码不正确"), request,
						response);
			}

		}

		return super.executeLogin(request, response);
	}

	/**
	 * 重写父类方法，当登录失败将异常信息设置到request的attribute中
	 */
	@Override
	protected void setFailureAttribute(ServletRequest request,
			AuthenticationException ae) {
		if (ae instanceof IncorrectCredentialsException) {
			request.setAttribute(getFailureKeyAttribute(), "用户名密码不正确");
		} else if (ae instanceof IncorrectCaptchaException) {
			request.setAttribute(getFailureKeyAttribute(), "验证码不正确");
		} else {
			request.setAttribute(getFailureKeyAttribute(), ae.getMessage());
		}
	}

	/**
	 * 重写父类方法，当登录失败次数大于allowIncorrectNumber（允许登录错误次数）时，将显示验证码
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token,
			AuthenticationException e, ServletRequest request,
			ServletResponse response) {

		Session session = SecurityUtils.getSubject().getSession();
		Integer number = (Integer) session
				.getAttribute(getLoginIncorrectNumberKeyAttribute());
		session.setAttribute(getLoginIncorrectNumberKeyAttribute(), ++number);

		return super.onLoginFailure(token, e, request, response);
	}

	/**
	 * 重写父类方法，当登录成功后，将allowIncorrectNumber（允许登错误录次）设置为0，重置下一次登录的状态
	 */
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token,
			Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {

		Session session = SecurityUtils.getSubject().getSession();
		session.removeAttribute(getLoginIncorrectNumberKeyAttribute());
		session.setAttribute("sv", subject.getPrincipal());

		return super.onLoginSuccess(token, subject, request, response);
	}

	@Override
	protected AuthenticationToken createToken(ServletRequest request,
			ServletResponse response) {
		// TODO Auto-generated method stub
		String username = getUsername(request);
		String password = getPassword(request);
		String captcha = getCaptcha(request);
		String accessToken = getToken(request);
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);
		return new CaptchaUsernamePasswordToken(username,
				password.toCharArray(), rememberMe, host, captcha, accessToken);
	}

	/**
	 * 创建一个shiro的session,如果存在session就用现有的session，否则创建一个新的session
	 * 
	 * @return {@link Session}
	 */
	public Session createSessionIfNull() {
		Session session = getSession();

		if (session == null) {
			session = getSession(true);
		}

		return session;
	}

	/**
	 * 获取shiro的session
	 * 
	 * @return {@link Session}
	 */
	public Session getSession() {
		return getSession(false);
	}

	/**
	 * 
	 * 获取shiro的session
	 * 
	 * @param create
	 *            true表示如果不存在，就创建，否则用现有的
	 * 
	 * @return {@link Session}
	 */
	public Session getSession(boolean create) {
		return SecurityUtils.getSubject().getSession(create);
	}

	/**
	 * 判断当前会话是否登录
	 * 
	 * @return boolean
	 */
	public boolean isAuthenticated() {
		return SecurityUtils.getSubject().isAuthenticated();
	}

	/**
	 * 设置验证码提交的参数名称
	 * 
	 * @param captchaParam
	 *            验证码提交的参数名称
	 */
	public void setCaptchaParam(String captchaParam) {
		this.captchaParam = captchaParam;
	}

	/**
	 * 获取验证码提交的参数名称
	 * 
	 * @return String
	 */
	public String getCaptchaParam() {
		return captchaParam;
	}

	public String getTokenParam() {
		return tokenParam;
	}

	public void setTokenParam(String tokenParam) {
		this.tokenParam = tokenParam;
	}

	/**
	 * 设置在session中的存储验证码的key名称
	 * 
	 * @param sessionCaptchaKeyAttribute
	 *            存储验证码的key名称
	 */
	public void setSessionCaptchaKeyAttribute(String sessionCaptchaKeyAttribute) {
		this.sessionCaptchaKeyAttribute = sessionCaptchaKeyAttribute;
	}

	/**
	 * 获取设置在session中的存储验证码的key名称
	 * 
	 * @return Sting
	 */
	public String getSessionCaptchaKeyAttribute() {
		return sessionCaptchaKeyAttribute;
	}

	/**
	 * 获取登录错误次数的key属性名称
	 * 
	 * @return String
	 */
	public String getLoginIncorrectNumberKeyAttribute() {
		return loginIncorrectNumberKeyAttribute;
	}

	/**
	 * 设置登录错误次数的key属性名称
	 * 
	 * @param loginIncorrectNumberKeyAttribute
	 *            属性名称
	 */
	public void setLoginIncorrectNumberKeyAttribute(
			String loginIncorrectNumberKeyAttribute) {
		this.loginIncorrectNumberKeyAttribute = loginIncorrectNumberKeyAttribute;
	}

	/**
	 * 获取用户输入的验证码
	 * 
	 * @param request
	 *            ServletRequest
	 * 
	 * @return String
	 */
	public String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

	public String getToken(ServletRequest request) {
		return WebUtils.getCleanParam(request, getTokenParam());
	}

	/**
	 * 获取允许登录次数
	 * 
	 * @return Integer
	 */
	public Integer getAllowIncorrectNumber() {
		return allowIncorrectNumber;
	}

	/**
	 * 设置允许登录次数，当登录次数大于该数值时，会在页面中显示验证码
	 * 
	 * @param allowIncorrectNumber
	 *            允许登录次数
	 */
	public void setAllowIncorrectNumber(Integer allowIncorrectNumber) {
		this.allowIncorrectNumber = allowIncorrectNumber;
	}
    
}
