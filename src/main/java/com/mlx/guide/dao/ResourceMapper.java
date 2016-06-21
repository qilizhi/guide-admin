package com.mlx.guide.dao;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.Resource;

public interface ResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);
    PageList<Resource> getResourcePageList(Resource resource, PageBounds pageBounds);

	void deleteResourceBitch(String[] ids);

	List<Resource> getResourceList(Resource resource);

	List<Resource> getResourcesByUserNo(String userNo);

	List<Resource> getResourceByUserNo(String userNo);

	List<Resource> getResourceByRoleId(Integer roleId);
}