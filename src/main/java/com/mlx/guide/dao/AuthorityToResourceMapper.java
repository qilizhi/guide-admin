package com.mlx.guide.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.AuthorityToResource;
import com.mlx.guide.entity.Resource;

public interface AuthorityToResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AuthorityToResource record);

    int insertSelective(AuthorityToResource record);

    AuthorityToResource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuthorityToResource record);

    int updateByPrimaryKey(AuthorityToResource record);
    
    List<AuthorityToResource> selectByExample(AuthorityToResource authorityToResource);
    PageList<AuthorityToResource> selectByExample(AuthorityToResource authorityToResource, PageBounds pageBounds);
    PageList<Resource> selectResourceByExample(Integer authorityId,PageBounds pageBounds);
    
    int batInsert(Map<String, Object> params);

    int deleteBySelective(AuthorityToResource authorityToResource);
    
}