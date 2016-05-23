package com.mlx.guide.dao;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.mlx.guide.entity.Authority;


public interface AuthorityMapper {
	
    int deleteByPrimaryKey(Integer id);
   
    int insert(Authority record);
   
    int insertSelective(Authority record);
   
    Authority selectByPrimaryKey(Integer id);
	
    int updateByPrimaryKeySelective(Authority record);
	
    int updateByPrimaryKey(Authority record);

    List<Authority> selectByParentId(Integer parentId);
	
    List<Authority> selectFirstParentId();

    List<Authority> list(Authority auth,PageBounds bounds);

	List<Authority> getAuhtoritysByUserNo(String userNo);
    
}