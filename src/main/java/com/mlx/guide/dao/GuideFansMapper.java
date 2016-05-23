package com.mlx.guide.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.GuideFans;
import com.mlx.guide.model.MyFans;

public interface GuideFansMapper {

	public List<GuideFans> getGuideFansList();

	public PageList<GuideFans> getGuideFansList(PageBounds pageBounds);

	public List<GuideFans> getGuideFansPageList(GuideFans guideFans);

	public PageList<GuideFans> getGuideFansPageList(GuideFans guideFans, PageBounds pageBounds);

	public List<GuideFans> getGuideFansPageListByMap(Map<String, Object> map);

	public PageList<GuideFans> getGuideFansPageListByMap(Map<String, Object> map, PageBounds pageBounds);

	public PageList<MyFans> getMyFansPageListByMap(Map<String, Object> map, PageBounds pageBounds);

	public GuideFans getGuideFansByPrimaryKey(Integer id);

	public void createGuideFans(GuideFans guideFans);

	public void createGuideFansSelective(GuideFans guideFans);

	public void updateGuideFans(GuideFans guideFans);

	public void updateGuideFansSelective(GuideFans guideFans);

	public void deleteGuideFans(Integer id);

	public int checkFanIsExist(Map<String, Object> map);

	public List<GuideFans> findFollowByUserNo(String userNo);

	public void cancelFollowFans(Map<String, Object> map);

	public int getTotalFansNum(String userNo);

	public List<GuideFans> getGuideRankingList(PageBounds pageBounds);
}