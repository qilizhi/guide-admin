package com.mlx.guide.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.GuideStrategy;

public interface GuideStrategyMapper {

	public List<GuideStrategy> getGuideStrategyList();

	public PageList<GuideStrategy> getGuideStrategyList(PageBounds pageBounds);

	public List<GuideStrategy> getGuideStrategyPageList(GuideStrategy guideStrategy);

	public PageList<GuideStrategy> getGuideStrategyPageList(GuideStrategy guideStrategy, PageBounds pageBounds);

	public List<GuideStrategy> getGuideStrategyPageListByMap(Map<String, Object> map);

	public PageList<GuideStrategy> getGuideStrategyPageListByMap(Map<String, Object> map, PageBounds pageBounds);

	public GuideStrategy getGuideStrategyByPrimaryKey(Integer id);

	public void createGuideStrategy(GuideStrategy guideStrategy);

	public void createGuideStrategySelective(GuideStrategy guideStrategy);

	public void updateGuideStrategy(GuideStrategy guideStrategy);

	public int updateGuideStrategySelective(GuideStrategy guideStrategy);

	public int deleteGuideStrategy(Integer id);

	public List<GuideStrategy> getGuideStrategyRankingList(PageBounds pageBounds);

	public List<GuideStrategy> getGuideStrategyByUserNo(String userNo);
}