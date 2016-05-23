package com.mlx.guide.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.GuideImgGroup;

public interface GuideImgGroupMapper {

	public List<GuideImgGroup> getGuideImgGroupList();

	public PageList<GuideImgGroup> getGuideImgGroupList(PageBounds pageBounds);

	public List<GuideImgGroup> getGuideImgGroupPageList(GuideImgGroup guideImgGroup);

	public PageList<GuideImgGroup> getGuideImgGroupPageList(GuideImgGroup guideImgGroup, PageBounds pageBounds);

	public List<GuideImgGroup> getGuideImgGroupPageListByMap(Map<String, Object> map);

	public PageList<GuideImgGroup> getGuideImgGroupPageListByMap(Map<String, Object> map, PageBounds pageBounds);

	public GuideImgGroup getGuideImgGroupByPrimaryKey(Integer id);

	public void createGuideImgGroup(GuideImgGroup guideImgGroup);

	public void createGuideImgGroupSelective(GuideImgGroup guideImgGroup);

	public void updateGuideImgGroup(GuideImgGroup guideImgGroup);

	public void updateGuideImgGroupSelective(GuideImgGroup guideImgGroup);

	public void deleteGuideImgGroup(Integer id);
}