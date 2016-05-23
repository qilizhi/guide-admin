package com.mlx.guide.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlx.guide.constant.Const;
import com.mlx.guide.constant.EUserType;
import com.mlx.guide.constant.ExceptionCode;
import com.mlx.guide.constant.JsonResult;
import com.mlx.guide.shiro.CaptchaUsernamePasswordToken;
import com.mlx.guide.shiro.ShiroDbRealm;
import com.mlx.guide.shiro.ShiroDbRealm.ShiroUser;
import com.mlx.guide.util.StringUtil;

/**
 * 登录和异常跳转处理
 * 
 * @author quan
 * 
 */
@Controller
@RequestMapping( "/" )
public class LoginController {

	private static Logger logger = LoggerFactory.getLogger( LoginController.class );

	/**
	 * 平台登录
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping( value = "platformLogin", method = RequestMethod.GET )
	public String platformLogin( @RequestParam( required = false ) String returnUrl, Model model ) {
		ShiroUser shiroUser = ShiroDbRealm.getLoginUser();
		if( shiroUser != null && shiroUser.getUserType() == EUserType.PLATFORM_USER ) {
			// 平台用户跳转到平台首页页面
			return "redirect:/admin";
		}
		else if( shiroUser != null && shiroUser.getUserType() == EUserType.GUIDE_USER ) {
			// 导游用户跳转到导游管理首页页面
			return "redirect:/guideAdmin";
		}
		else if( shiroUser != null && shiroUser.getUserType() == EUserType.USER ) {
			// 普通用户跳转到403页面
			return "redirect:/error/403";
		}
		model.addAttribute( "returnUrl", returnUrl );
		return "admin/login";
	}

	/**
	 * 平台登录
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping( value = "platformLogin", method = RequestMethod.POST )
	@ResponseBody
	public JsonResult platformLogin( HttpServletRequest request,
	        @CookieValue( value = Const.LOGIN_PARAM, required = false ) String accessToken ) {
		return loginToRedirect( request, accessToken, EUserType.PLATFORM_USER );
	}

	/**
	 * 导游登录
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping( value = "guideLogin", method = RequestMethod.GET )
	public String guideAdminLogin( @RequestParam( required = false ) String returnUrl, Model model ) {
		ShiroUser shiroUser = ShiroDbRealm.getLoginUser();
		if( shiroUser != null && shiroUser.getUserType() == EUserType.PLATFORM_USER ) {
			// 平台用户跳转到平台首页页面
			return "redirect:/admin";
		}
		else if( shiroUser != null && shiroUser.getUserType() == EUserType.GUIDE_USER ) {
			// 导游用户跳转到导游管理首页页面
			return "redirect:/guideAdmin";
		}
		else if( shiroUser != null && shiroUser.getUserType() == EUserType.USER ) {
			// 普通用户跳转到403页面
			return "redirect:/error/403";
		}
		model.addAttribute( "returnUrl", returnUrl );
		return "guideAdmin/login";
	}

	/**
	 * 导游登录
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping( value = "guideLogin", method = RequestMethod.POST )
	@ResponseBody
	public JsonResult guideAdminLogin( HttpServletRequest request,
	        @CookieValue( value = Const.LOGIN_PARAM, required = false ) String accessToken ) {
		return loginToRedirect( request, accessToken, EUserType.GUIDE_USER );
	}

	/**
	 * 登录
	 * 
	 * @param request
	 * @param defUrl
	 * @return
	 */
	private JsonResult loginToRedirect( HttpServletRequest request, String accessToken, EUserType eUserType ) {
		String loginName = StringUtil.stringValue( request.getParameter( "username" ), "" );
		String password = StringUtil.stringValue( request.getParameter( "password" ), "" );
		boolean rememberMe =  request.getParameter( "remember" ) != null;
		if( StringUtil.empty( loginName ) || StringUtil.empty( password ) ) {
			return new JsonResult( ExceptionCode.FAIL );
		}
		ShiroUser shiroUser = ShiroDbRealm.getLoginUser();
		if( shiroUser == null ) {
			try {
				CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken( loginName, password.toCharArray() );
				token.setRememberMe(rememberMe);
				token.setUserType( eUserType );
				token.setAccessToken( accessToken );
				SecurityUtils.getSubject().login( token );
				return new JsonResult( ExceptionCode.SUCCESSFUL);
			}
			catch( AuthenticationException e ) {
				logger.info( "登陆验证失败" + e.getMessage() );
				return new JsonResult( ExceptionCode.FAIL );
			}
		}
		return new JsonResult( ExceptionCode.SUCCESSFUL );
	}

	/**
	 * 登出
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping( "/logout" )
	public String logout( Model model ) {
		try {
			// 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
			SecurityUtils.getSubject().logout();
			model.addAttribute( "message", "您已安全退出" );
		}
		catch( Exception e ) {
			logger.error( e.getMessage() );
			model.addAttribute( "message", "退出失败,请重试." );
		}
		return "logout";
	}

	/**
	 * 异常错误
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping( "/error/{code}" )
	public String logout( @PathVariable String code, Model model ) {
		return "error/" + code;
	}

}
