package com.mlx.guide.dao;

import java.util.List;
import java.util.Map;

import com.mlx.guide.entity.UserToRole;

public interface UserToRoleMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(UserToRole record);

	int insertSelective(UserToRole record);

	UserToRole selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(UserToRole record);

	int updateByPrimaryKey(UserToRole record);
	List<UserToRole> selectByUserNo(String userNo);
	 void batDelete(Map<String,Object> params);
	 void batInsert(Map<String,Object> params);
}