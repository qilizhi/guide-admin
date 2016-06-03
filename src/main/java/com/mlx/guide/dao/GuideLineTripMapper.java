package com.mlx.guide.dao;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.GuideLineTrip;

public interface GuideLineTripMapper {
	int deleteByPrimaryKey(Long id);

	int insert(GuideLineTrip record);

	int insertSelective(GuideLineTrip record);

	GuideLineTrip selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(GuideLineTrip record);

	int updateByPrimaryKey(GuideLineTrip record);

	List<GuideLineTrip> getGuideLineTripPageList(GuideLineTrip record);

	PageList<GuideLineTrip> getGuideLineTripPageList(GuideLineTrip record, PageBounds pageBounds);
	
	void deleteGuideLineTripByLineNo(String lineNo);
}