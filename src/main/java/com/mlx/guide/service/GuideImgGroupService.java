package com.mlx.guide.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.dao.GuideImgGroupMapper;
import com.mlx.guide.entity.GuideImgGroup;

@Service
@Transactional
public class GuideImgGroupService {

	@Autowired
	private GuideImgGroupMapper guideImgGroupMapper;

	public List<GuideImgGroup> getGuideImgGroupList() {
		return guideImgGroupMapper.getGuideImgGroupList();
	}

	public PageList<GuideImgGroup> getGuideImgGroupList(PageBounds pageBounds) {
		return guideImgGroupMapper.getGuideImgGroupList(pageBounds);
	}

	public List<GuideImgGroup> getGuideImgGroupPageList(GuideImgGroup guideImgGroup) {
		return guideImgGroupMapper.getGuideImgGroupPageList(guideImgGroup);
	}

	public PageList<GuideImgGroup> getGuideImgGroupPageList(GuideImgGroup guideImgGroup, PageBounds pageBounds) {
		return guideImgGroupMapper.getGuideImgGroupPageList(guideImgGroup, pageBounds);
	}

	public List<GuideImgGroup> getGuideImgGroupPageListByMap(Map<String, Object> map) {
		return guideImgGroupMapper.getGuideImgGroupPageListByMap(map);
	}

	public PageList<GuideImgGroup> getGuideImgGroupPageListByMap(Map<String, Object> map, PageBounds pageBounds) {
		return guideImgGroupMapper.getGuideImgGroupPageListByMap(map, pageBounds);
	}

	public GuideImgGroup getGuideImgGroupByPrimaryKey(Integer id) {
		return guideImgGroupMapper.getGuideImgGroupByPrimaryKey(id);
	}

	public void createGuideImgGroup(GuideImgGroup guideImgGroup) {
		guideImgGroupMapper.createGuideImgGroup(guideImgGroup);
	}

	public void createGuideImgGroupSelective(GuideImgGroup guideImgGroup) {
		guideImgGroupMapper.createGuideImgGroupSelective(guideImgGroup);
	}

	public void createGuideImgGroupBitch(List<GuideImgGroup> guideImgGroupList) {
		for (GuideImgGroup guideImgGroup : guideImgGroupList) {
			guideImgGroupMapper.createGuideImgGroup(guideImgGroup);
		}
	}

	public void updateGuideImgGroup(GuideImgGroup guideImgGroup) {
		guideImgGroupMapper.updateGuideImgGroup(guideImgGroup);
	}

	public void updateGuideImgGroupBitch(List<GuideImgGroup> guideImgGroupList) {
		for (GuideImgGroup guideImgGroup : guideImgGroupList) {
			guideImgGroupMapper.updateGuideImgGroup(guideImgGroup);
		}
	}

	public void updateGuideImgGroupSelective(GuideImgGroup guideImgGroup) {
		guideImgGroupMapper.updateGuideImgGroupSelective(guideImgGroup);
	}

	public void updateGuideImgGroupSelectiveBitch(List<GuideImgGroup> guideImgGroupList) {
		for (GuideImgGroup guideImgGroup : guideImgGroupList) {
			guideImgGroupMapper.updateGuideImgGroupSelective(guideImgGroup);
		}
	}

	public void deleteGuideImgGroup(Integer id) {
		guideImgGroupMapper.deleteGuideImgGroup(id);
	}

	public void deleteGuideImgGroupBitch(List<Integer> idList) {
		for (Integer id : idList) {
			guideImgGroupMapper.deleteGuideImgGroup(id);
		}
	}

}