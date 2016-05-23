package com.mlx.guide.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.GuideImgInfo;

public interface GuideImgInfoMapper {

	public List<GuideImgInfo> getGuideImgInfoList();

	public PageList<GuideImgInfo> getGuideImgInfoList(PageBounds pageBounds);

	public List<GuideImgInfo> getGuideImgInfoPageList(GuideImgInfo guideImgInfo);

	public PageList<GuideImgInfo> getGuideImgInfoPageList(GuideImgInfo guideImgInfo, PageBounds pageBounds);

	public List<GuideImgInfo> getGuideImgInfoPageListByMap(Map<String, Object> map);

	public PageList<GuideImgInfo> getGuideImgInfoPageListByMap(Map<String, Object> map, PageBounds pageBounds);

	public GuideImgInfo getGuideImgInfoByPrimaryKey(Integer id);

	public void createGuideImgInfo(GuideImgInfo guideImgInfo);

	public void createGuideImgInfoSelective(GuideImgInfo guideImgInfo);

	public void updateGuideImgInfo(GuideImgInfo guideImgInfo);

	public void updateGuideImgInfoSelective(GuideImgInfo guideImgInfo);

	public void deleteGuideImgInfo(Integer id);
}