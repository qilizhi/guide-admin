package com.mlx.guide.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.GuideStrategyBrowser;

public interface GuideStrategyBrowserMapper {

	public List<GuideStrategyBrowser> getGuideStrategyBrowserList();

	public PageList<GuideStrategyBrowser> getGuideStrategyBrowserList(PageBounds pageBounds);

	public List<GuideStrategyBrowser> getGuideStrategyBrowserPageList(GuideStrategyBrowser guideStrategyBrowser);

	public PageList<GuideStrategyBrowser> getGuideStrategyBrowserPageList(GuideStrategyBrowser guideStrategyBrowser,
			PageBounds pageBounds);

	public List<GuideStrategyBrowser> getGuideStrategyBrowserPageListByMap(Map<String, Object> map);

	public PageList<GuideStrategyBrowser> getGuideStrategyBrowserPageListByMap(Map<String, Object> map,
			PageBounds pageBounds);

	public GuideStrategyBrowser getGuideStrategyBrowserByPrimaryKey(Integer id);

	public void createGuideStrategyBrowser(GuideStrategyBrowser guideStrategyBrowser);

	public void createGuideStrategyBrowserSelective(GuideStrategyBrowser guideStrategyBrowser);

	public void updateGuideStrategyBrowser(GuideStrategyBrowser guideStrategyBrowser);

	public void updateGuideStrategyBrowserSelective(GuideStrategyBrowser guideStrategyBrowser);

	public void deleteGuideStrategyBrowser(Integer id);

	public Long countByStrategyId(Integer strategyId);
}