package com.mlx.guide.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.GuideTuan;

public interface GuideTuanMapper {
	int deleteByPrimaryKey(Long id);

	int insert(GuideTuan record);

	int insertSelective(GuideTuan record);

	GuideTuan selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(GuideTuan record);

	int updateByPrimaryKey(GuideTuan record);

	PageList<GuideTuan> getGuideTuanPageListByMap(Map<String, Object> map, PageBounds pageBounds);

	List<GuideTuan> getGuideTuanPageList(GuideTuan record);

	PageList<GuideTuan> getGuideTuanPageList(GuideTuan guideTuan, PageBounds pageBounds);

	long countByDate(Integer month);
	
	void deleteGuideTuanByLineNo(String goodsNo);

}