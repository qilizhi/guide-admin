package com.mlx.guide.dao;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.GuideTuanGuest;

public interface GuideTuanGuestMapper {
	int deleteByPrimaryKey(Long id);

	int insert(GuideTuanGuest record);

	int insertSelective(GuideTuanGuest record);

	GuideTuanGuest selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(GuideTuanGuest record);

	int updateByPrimaryKey(GuideTuanGuest record);

	List<GuideTuanGuest> getGuideTuanGuestPageList(GuideTuanGuest record);

	PageList<GuideTuanGuest> getGuideTuanGuestPageList(GuideTuanGuest record, PageBounds pageBounds);
}