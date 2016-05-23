package com.mlx.guide.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.UserInfo;
import com.mlx.guide.model.Statistics;
import com.mlx.guide.model.UserInfoModel;

public interface UserInfoMapper {

	public List<UserInfo> getUserInfoList();

	public PageList<UserInfo> getUserInfoList(PageBounds pageBounds);

	public List<UserInfo> getUserInfoPageList(UserInfo userInfo);

	public PageList<UserInfo> getUserInfoPageList(UserInfo userInfo, PageBounds pageBounds);

	public List<UserInfo> getUserInfoPageListByMap(Map<String, Object> map);

	public PageList<UserInfo> getUserInfoPageListByMap(Map<String, Object> map, PageBounds pageBounds);

	public UserInfo selectByPrimaryKey(Long id);

	public void insert(UserInfo userInfo);

	public void insertSelective(UserInfo userInfo);

	public int updateByPrimaryKey(UserInfo userInfo);

	public void updateByPrimaryKeySelective(UserInfo userInfo);

	public int deleteByPrimaryKey(Long id);

	public UserInfo searchByName(String name);

	public List<Statistics> countUserGroupByDate(Integer userType);

	public PageList<UserInfo> getUserInfoPageListByModel(UserInfoModel userInfoModel, PageBounds pageBounds);

	
	
}