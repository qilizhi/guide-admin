package com.mlx.guide.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.GuideLine;

public interface GuideLineMapper {

	 List<GuideLine> getGuideLineList();

	 PageList<GuideLine> getGuideLineList(PageBounds pageBounds);

	 List<GuideLine> getGuideLinePageList(GuideLine guideLine);

	 PageList<GuideLine> getGuideLinePageList(GuideLine guideLine, PageBounds pageBounds);

	 List<GuideLine> getGuideLinePageListByMap(Map<String, Object> map);

	 PageList<GuideLine> getGuideLinePageListByMap(Map<String, Object> map, PageBounds pageBounds);

	 GuideLine getGuideLineByPrimaryKey(Integer id);

	 GuideLine getGuideLineByLineNo(String lineNo);

	 void createGuideLine(GuideLine guideLine);

	 void createGuideLineSelective(GuideLine guideLine);

	 void updateGuideLine(GuideLine guideLine);

	 int updateGuideLineSelective(GuideLine guideLine);

	 int deleteGuideLine(Integer id);

	 PageList<GuideLine> getGuideLineByUserNoPageList(String userNo, GuideLine guideLine, PageBounds pageBounds);

	 PageList<GuideLine> searcheGuideLinePageList(GuideLine guideLine, PageBounds pageBounds);

	 List<GuideLine> getGuideLineByUserNo(String userNo);
}