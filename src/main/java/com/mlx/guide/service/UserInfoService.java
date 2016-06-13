package com.mlx.guide.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.constant.Const;
import com.mlx.guide.dao.AuthorityMapper;
import com.mlx.guide.dao.ResourceMapper;
import com.mlx.guide.dao.RoleMapper;
import com.mlx.guide.dao.UserInfoMapper;
import com.mlx.guide.entity.Authority;
import com.mlx.guide.entity.Resource;
import com.mlx.guide.entity.Role;
import com.mlx.guide.entity.UserInfo;
import com.mlx.guide.model.Member;
import com.mlx.guide.model.Statistics;
import com.mlx.guide.model.UserInfoModel;
import com.mlx.guide.util.Digests;
import com.mlx.guide.util.Encodes;

@Service
@Transactional
@CacheConfig(cacheNames="mlx",keyGenerator="keyGenerator")
public class UserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private AuthorityMapper authorityMapper;
	@Autowired
	private ResourceMapper resourceMapper;
	
	@Cacheable
	public List<UserInfo> getUserInfoList() {
		return userInfoMapper.getUserInfoList();
	}

	@Cacheable
	public PageList<UserInfo> getUserInfoList(PageBounds pageBounds) {
		return userInfoMapper.getUserInfoList(pageBounds);
	}
	@Cacheable
	public List<UserInfo> getUserInfoPageList(UserInfo userInfo) {
		return userInfoMapper.getUserInfoPageList(userInfo);
	}
	@Cacheable
	public PageList<UserInfo> getUserInfoPageList(UserInfo userInfo, PageBounds pageBounds) {
		return userInfoMapper.getUserInfoPageList(userInfo, pageBounds);
	}
	@Cacheable
	public PageList<UserInfo> getUserInfoPageList(UserInfoModel userInfoModel, PageBounds pageBounds) {
		return userInfoMapper.getUserInfoPageListByModel(userInfoModel, pageBounds);
	}
	@Cacheable
	public List<UserInfo> getUserInfoPageListByMap(Map<String, Object> map) {
		return userInfoMapper.getUserInfoPageListByMap(map);
	}

	public PageList<UserInfo> getUserInfoPageListByMap(Map<String, Object> map, PageBounds pageBounds) {
		return userInfoMapper.getUserInfoPageListByMap(map, pageBounds);
	}

	public UserInfo getUserInfoByPrimaryKey(Long id) {
		return userInfoMapper.selectByPrimaryKey(id);
	}
	 @CachePut
	public void createUserInfo(UserInfo userInfo) {
		userInfoMapper.insert(userInfo);
	}
	 @CachePut
	public void createUserInfoSelectivew(UserInfo userInfo) {
		userInfoMapper.insertSelective(userInfo);
	}
	 @CachePut
	public void createUserInfoBitch(List<UserInfo> userInfoList) {
		/*for (UserInfo userInfo : userInfoList) {
			userInfoMapper.insert(userInfo);
		}*/
	}
	@CacheEvict(allEntries = true)
	public int updateUserInfo(UserInfo userInfo) {
		return userInfoMapper.updateByPrimaryKey(userInfo);
	}
	@CacheEvict(allEntries = true)
	public void updateUserInfoBitch(List<UserInfo> userInfoList) {
		/*for (UserInfo userInfo : userInfoList) {
			userInfoMapper.updateByPrimaryKey(userInfo);
		}*/
	}
	@CacheEvict(allEntries = true)
	public void updateUserInfoSelective(UserInfo userInfo) {
		userInfoMapper.updateByPrimaryKeySelective(userInfo);
	}
	@CacheEvict(allEntries = true)
	public void updateUserInfoSelectiveBitch(List<UserInfo> userInfoList) {
		/*for (UserInfo userInfo : userInfoList) {
			userInfoMapper.updateByPrimaryKeySelective(userInfo);
		}*/
	}
	@CacheEvict( allEntries = true)
	public int deleteUserInfo(Long id) {
		return userInfoMapper.deleteByPrimaryKey(id);
	}
	@CacheEvict( allEntries = true)
	public void deleteUserInfoBitch(List<Long> idList) {
		for (Long id : idList) {
			userInfoMapper.deleteByPrimaryKey(id);
		}
	}
	public UserInfo getSearcheByName(String name) {
		// TODO Auto-generated method stub
		return userInfoMapper.searchByName(name);
	}

	@Cacheable
	public List<Role> getRolesByUserNo(String userNo) {

		return roleMapper.getRolesByUserNo(userNo);
	}
	@Cacheable
	public List<Authority> getAuthoritysByUserNo(String userNo) {

		return authorityMapper.getAuhtoritysByUserNo(userNo);
	}
	@Cacheable
	public List<Resource> getResourcesByUserNo(String userNo) {

		return resourceMapper.getResourceByUserNo(userNo);
	}

	// 用户统计
	@Cacheable
	public List<Statistics> countUserGroupByDate(Integer userType) {

		return userInfoMapper.countUserGroupByDate(userType);
	}

	private void entryptPassword(UserInfo user) {
		if (user == null || user.getPassword() == null)
			return;
		byte[] salt = Digests.generateSalt(Const.SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));
		byte[] hashPassword = Digests.sha1(user.getPassword().getBytes(), salt, Const.HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	public void resetPassword(UserInfo user) {
		entryptPassword(user);
		userInfoMapper.updateByPrimaryKeySelective(user);

 	}

	public UserInfo getUserInfoById(Long id) {
		return userInfoMapper.selectByPrimaryKey(id);
		
	}
    
	
	

}