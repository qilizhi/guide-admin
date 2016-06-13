/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.mlx.guide.shiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mlx.guide.constant.Const;
import com.mlx.guide.constant.EUserType;
import com.mlx.guide.entity.GuideInfo;
import com.mlx.guide.entity.Resource;
import com.mlx.guide.entity.Role;
import com.mlx.guide.entity.UserInfo;
import com.mlx.guide.model.PlatformUser;
import com.mlx.guide.service.GuideInfoService;
import com.mlx.guide.service.UserInfoService;
import com.mlx.guide.shiro.http.HttpCredentialsMatcher;
import com.mlx.guide.shiro.http.HttpOAuthAuthenticationInfo;
import com.mlx.guide.util.Digests;
import com.mlx.guide.util.Encodes;
import com.mlx.guide.util.MlxingAPIUtil;
import com.mlx.guide.util.ParamUtil;
import com.mlx.guide.util.StringUtil;

@Component("shiroDbRealm")
public class ShiroDbRealm extends AuthorizingRealm {

	private static Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);

	/**
	 * 平台用户登录接口地址
	 */
	private static final String PLATFORM_LOGIN = "http://passport.mlxing.com/api/platformUser/login";

	/**
	 * 平台用户信息地址
	 */
	private static final String PLATFORM_USERINFO_LOGIN = "http://passport.mlxing.com/api/platformUser/getUserInfo";

	private UserInfoService userInfoService;
	
	@Autowired
	private GuideInfoService guideInfoService;

	@Autowired
	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authcToken;
		String password = token.getPassword() == null ? null : String.valueOf(token.getPassword());
		try {
			if (token.getUserType() == EUserType.PLATFORM_USER) {
				ParamUtil paramUtil = ParamUtil.getNewInstance();
				paramUtil.addParam(Const.API_LOGIN_NAME, token.getUsername());
				paramUtil.addParam(Const.TOKEN_PARAM, token.getAccessToken());
				paramUtil.addParam(Const.API_PARAM_PASSWORD, password);
				PlatformUser platformUser = null;
				if (StringUtil.empty(token.getAccessToken())) {
					platformUser = MlxingAPIUtil.getAPIDataT( PLATFORM_LOGIN, paramUtil, PlatformUser.class );
				} else {
					platformUser = MlxingAPIUtil.getAPIDataT( PLATFORM_USERINFO_LOGIN, paramUtil, PlatformUser.class );
				}
				if (platformUser != null) {
					ShiroUser user1 = new ShiroUser(token.getUsername(), platformUser.getName(),platformUser.getUserNo(), null, token.getUserType());
					HttpOAuthAuthenticationInfo ai = new HttpOAuthAuthenticationInfo(user1, token.getUsername(), true,user1.getName());
					return ai;
				}
				return null;
			}

			UserInfo userInfo = new UserInfo();
			userInfo.setMobile(token.getUsername());
			List<UserInfo> lsUserInfos = userInfoService.getUserInfoPageList(userInfo);
			if (lsUserInfos == null || lsUserInfos.isEmpty()) {
				return null;
			}
			userInfo = lsUserInfos.get(0);
			GuideInfo guideInfo = guideInfoService.getGuideInfoByUserNo(userInfo.getUserNo());
			
			if(guideInfo == null || guideInfo.getRealName() == null){
				logger.error("验证登录错误: 非导游账号");
				return null;
			}
			if(!checkPwd( userInfo.getSalt(), password, userInfo.getPassword() )){
				return null;
			}
			// TODO:需要进行密码加密处理进行验证
			ShiroUser user1 = new ShiroUser(userInfo.getMobile(), guideInfo.getRealName(), userInfo.getUserNo(),userInfo.getOpenId(), token.getUserType());
			HttpOAuthAuthenticationInfo ai = new HttpOAuthAuthenticationInfo(user1, token.getUsername(), true, user1.getName());
			return ai;
		} catch (AuthenticationException e) {
			logger.error("验证登录错误: " + e.getMessage());
			throw new AuthenticationException(e.getMessage());
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.在配有缓存的情况下，只加载一次.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// 获取登录时输入的用户名
		ShiroUser shiroUser = (ShiroUser) principalCollection.getPrimaryPrincipal();

		// 到数据库查是否有此对象
		if (shiroUser != null) {
			// 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			// 用户的角色集合
			Set<String> roles = new HashSet<String>();
			roles.add(shiroUser.role);
			//查找系统给用户分配的角色。
			List<Role> rolesList=userInfoService.getRolesByUserNo(shiroUser.getUserNo());
			for(Role r:rolesList){
				roles.add(r.getName());
			}
			
			info.setRoles(roles);
			// 用户的角色对应的所有权限，如果只使用角色定义访问权限，下面的四行可以不要
			List<String> perms = new ArrayList<String>();
		    // perms.addAll(info.getStringPermissions());
			if (perms != null && perms.size() <= 0) {
				logger.info("perms is null");
				perms.add("undefined:*");// 无权限时默认给个权限
			}
			perms.add("supplierAdmin:view");
			List<Resource> resources=userInfoService.getResourcesByUserNo(shiroUser.getUserNo());
			for(Resource r:resources){
				perms.add(r.getPath());				
			}
			info.addStringPermissions(perms);
			logger.info(info.getStringPermissions().toString());
			logger.info(info.getRoles().toString());
			return info;
		}
		return null;
	}
	
	/**
	 * 检查密码是否匹配
	 * 
	 * @param cmlSalt
	 * @param inputPwd
	 * @param cmlPwd
	 * @return
	 */
	private boolean checkPwd( String cmlSalt, String inputPwd, String cmlPwd ) {
		if( StringUtil.empty( cmlSalt ) || StringUtil.empty( inputPwd ) || StringUtil.empty( cmlPwd ) ) {
			return false;
		}
		String salt = StringUtil.stringValue( cmlSalt, "" );
		String hexPwd = Encodes.encodeHex( Digests.sha1( inputPwd.getBytes(), Encodes.decodeHex( salt ), Const.SALT_SIZE ) );
		return (cmlPwd != null && !StringUtil.empty( hexPwd ) && cmlPwd.equals( hexPwd ));
	}

	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(Object principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

	/**
	 * 设定Password校验的Hash算法与迭代次数.
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		//HashedCredentialsMatcher matcher = new
		 //HashedCredentialsMatcher(Const.HASH_ALGORITHM);
		//matcher.setHashIterations(Const.HASH_INTERATIONS);

		HttpCredentialsMatcher matcher = new HttpCredentialsMatcher();
		setCredentialsMatcher(matcher);
	}

	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public static class ShiroUser implements Serializable {
		private static final long serialVersionUID = -1373760761780840081L;

		public String loginName;

		public String name;

		public String userNo;

		public String openid;

		public String role;

		public EUserType userType;

		public ShiroUser(String loginName, String name, String userNo, String openid, EUserType userType) {
			this.loginName = loginName;
			this.name = name;
			this.userNo = userNo;
			this.role = userType == EUserType.PLATFORM_USER ? "admin" : "guideAdmin";
			this.openid = openid;
			this.userType = userType;
		}

		public String getName() {
			return name;
		}

		public String getUserNo() {
			return userNo;
		}

		public String getRole() {
			return role;
		}

		public String getOpenid() {
			return openid;
		}

		public EUserType getUserType() {
			return userType;
		}

		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString() {
			return name;
		}

		/**
		 * 重载hashCode,只计算loginName;
		 */
		@Override
		public int hashCode() {
			return Objects.hashCode(loginName);
		}

		/**
		 * 重载equals,只计算loginName;
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			ShiroUser other = (ShiroUser) obj;
			if (loginName == null) {
				if (other.loginName != null) {
					return false;
				}
			} else if (!loginName.equals(other.loginName)) {
				return false;
			}
			return true;
		}
	}

	/**
	 * 得到当前登录用户
	 * 
	 * @return
	 */
	public static ShiroUser getLoginUser() {
		return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
	}

	/**
	 * 取出Shiro中的当前用户userNo.
	 */
	public static String getCurrentUserNo() {
		ShiroUser user = getLoginUser();
		if (user != null) {
			return user.getUserNo();
		}
		return null;
	}

}
