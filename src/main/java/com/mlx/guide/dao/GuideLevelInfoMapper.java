package com.mlx.guide.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.GuideLevelInfo;

public interface GuideLevelInfoMapper {

	public List<GuideLevelInfo> getGuideLevelInfoList();

	public PageList<GuideLevelInfo> getGuideLevelInfoList(PageBounds pageBounds);

	public List<GuideLevelInfo> getGuideLevelInfoPageList(GuideLevelInfo guideLevelInfo);

	public PageList<GuideLevelInfo> getGuideLevelInfoPageList(GuideLevelInfo guideLevelInfo, PageBounds pageBounds);

	public List<GuideLevelInfo> getGuideLevelInfoPageListByMap(Map<String, Object> map);

	public PageList<GuideLevelInfo> getGuideLevelInfoPageListByMap(Map<String, Object> map, PageBounds pageBounds);

	public GuideLevelInfo getGuideLevelInfoByPrimaryKey(Integer id);

	public void createGuideLevelInfo(GuideLevelInfo guideLevelInfo);

	public void createGuideLevelInfoSelective(GuideLevelInfo guideLevelInfo);

	public void updateGuideLevelInfo(GuideLevelInfo guideLevelInfo);

	public void updateGuideLevelInfoSelective(GuideLevelInfo guideLevelInfo);

	public void deleteGuideLevelInfo(Integer id);
}