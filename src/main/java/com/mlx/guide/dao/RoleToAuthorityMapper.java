package com.mlx.guide.dao;

import java.util.List;
import java.util.Map;

import com.mlx.guide.entity.RoleToAuthority;

public interface RoleToAuthorityMapper {

	int deleteByPrimaryKey(Integer id);

	
	int insert(RoleToAuthority record);

	
	int insertSelective(RoleToAuthority record);

	
	RoleToAuthority selectByPrimaryKey(Integer id);

	
	int updateByPrimaryKeySelective(RoleToAuthority record);


	int updateByPrimaryKey(RoleToAuthority record);


	int deleteByRoleId(Integer roleId);
    int batInsert(Map<String, Object> params);
	List<RoleToAuthority> selectByRoleId(Integer roleId);

	int batDelete(Map<String, Object> params);
	 void deleteByRoleToAuthority(RoleToAuthority rta);
}