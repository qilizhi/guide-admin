package com.mlx.guide.dao;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.mlx.guide.entity.Role;


public interface RoleMapper {
	
	int deleteByPrimaryKey(Integer id);
    
	int insert(Role record);
    
	int insertSelective(Role record);
    
	Role selectByPrimaryKey(Integer id);
	
	int updateByPrimaryKeySelective(Role record);
	
	int updateByPrimaryKey(Role record);
	
	List<Role> list(Role role, PageBounds bounds);
	
	List<Role> selectByParentId(Integer parentId);
	
	List<Role> selectFirstParentId();
	List<Role> getRolesByUserNo(String userNo);
}