package com.mlx.guide.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.dao.GuideFansMapper;
import com.mlx.guide.entity.GuideFans;
import com.mlx.guide.model.MyFans;

@Service
@Transactional
public class GuideFansService {

	@Autowired
	private GuideFansMapper guideFansMapper;

	public List<GuideFans> getGuideFansList() {
		return guideFansMapper.getGuideFansList();
	}

	public PageList<GuideFans> getGuideFansList(PageBounds pageBounds) {
		return guideFansMapper.getGuideFansList(pageBounds);
	}

	public List<GuideFans> getGuideFansPageList(GuideFans guideFans) {
		return guideFansMapper.getGuideFansPageList(guideFans);
	}

	public PageList<GuideFans> getGuideFansPageList(GuideFans guideFans, PageBounds pageBounds) {
		return guideFansMapper.getGuideFansPageList(guideFans, pageBounds);
	}

	public List<GuideFans> getGuideFansPageListByMap(Map<String, Object> map) {
		return guideFansMapper.getGuideFansPageListByMap(map);
	}

	public PageList<GuideFans> getGuideFansPageListByMap(Map<String, Object> map, PageBounds pageBounds) {
		return guideFansMapper.getGuideFansPageListByMap(map, pageBounds);
	}

	public PageList<MyFans> getMyFansPageListByMap(Map<String, Object> map, PageBounds pageBounds) {
		return guideFansMapper.getMyFansPageListByMap(map, pageBounds);
	}

	public GuideFans getGuideFansByPrimaryKey(Integer id) {
		return guideFansMapper.getGuideFansByPrimaryKey(id);
	}

	public void createGuideFans(GuideFans guideFans) {
		guideFansMapper.createGuideFans(guideFans);
	}

	public void createGuideFansSelective(GuideFans guideFans) {
		guideFansMapper.createGuideFansSelective(guideFans);
	}

	public void createGuideFansBitch(List<GuideFans> guideFansList) {
		for (GuideFans guideFans : guideFansList) {
			guideFansMapper.createGuideFans(guideFans);
		}
	}

	public void updateGuideFans(GuideFans guideFans) {
		guideFansMapper.updateGuideFans(guideFans);
	}

	public void updateGuideFansBitch(List<GuideFans> guideFansList) {
		for (GuideFans guideFans : guideFansList) {
			guideFansMapper.updateGuideFans(guideFans);
		}
	}

	public void updateGuideFansSelective(GuideFans guideFans) {
		guideFansMapper.updateGuideFansSelective(guideFans);
	}

	public void updateGuideFansSelectiveBitch(List<GuideFans> guideFansList) {
		for (GuideFans guideFans : guideFansList) {
			guideFansMapper.updateGuideFansSelective(guideFans);
		}
	}

	public void deleteGuideFans(Integer id) {
		guideFansMapper.deleteGuideFans(id);
	}

	public void deleteGuideFansBitch(List<Integer> idList) {
		for (Integer id : idList) {
			guideFansMapper.deleteGuideFans(id);
		}
	}

	public int checkFanIsExist(String parent_user_no, String sub_user_no) {
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put("parentUserNo", parent_user_no);
		map.put("subUserNo", sub_user_no);
		return guideFansMapper.checkFanIsExist(map);
	}

	public List<GuideFans> findFollowByUserNo(String userNo) {
		return guideFansMapper.findFollowByUserNo(userNo);
	}

	public void cancelFollowFans(String parentUserNo, String subUserNo) {
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put("parentUserNo", parentUserNo);
		map.put("subUserNo", subUserNo);
		guideFansMapper.cancelFollowFans(map);
	}

	public int getTotalFansNum(String userNo) {
		return guideFansMapper.getTotalFansNum(userNo);
	}

	public List<GuideFans> getGuideRankingList(PageBounds pageBounds) {
		return guideFansMapper.getGuideRankingList(pageBounds);
	}
}