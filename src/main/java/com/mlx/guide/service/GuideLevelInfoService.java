package com.mlx.guide.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.dao.GuideLevelInfoMapper;
import com.mlx.guide.entity.GuideLevelInfo;

@Service
@Transactional
public class GuideLevelInfoService {

	@Autowired
	private GuideLevelInfoMapper guideLevelInfoMapper;

	public List<GuideLevelInfo> getGuideLevelInfoList() {
		return guideLevelInfoMapper.getGuideLevelInfoList();
	}

	public PageList<GuideLevelInfo> getGuideLevelInfoList(PageBounds pageBounds) {
		return guideLevelInfoMapper.getGuideLevelInfoList(pageBounds);
	}

	public List<GuideLevelInfo> getGuideLevelInfoPageList(GuideLevelInfo guideLevelInfo) {
		return guideLevelInfoMapper.getGuideLevelInfoPageList(guideLevelInfo);
	}

	public PageList<GuideLevelInfo> getGuideLevelInfoPageList(GuideLevelInfo guideLevelInfo, PageBounds pageBounds) {
		return guideLevelInfoMapper.getGuideLevelInfoPageList(guideLevelInfo, pageBounds);
	}

	public List<GuideLevelInfo> getGuideLevelInfoPageListByMap(Map<String, Object> map) {
		return guideLevelInfoMapper.getGuideLevelInfoPageListByMap(map);
	}

	public PageList<GuideLevelInfo> getGuideLevelInfoPageListByMap(Map<String, Object> map, PageBounds pageBounds) {
		return guideLevelInfoMapper.getGuideLevelInfoPageListByMap(map, pageBounds);
	}

	public GuideLevelInfo getGuideLevelInfoByPrimaryKey(Integer id) {
		return guideLevelInfoMapper.getGuideLevelInfoByPrimaryKey(id);
	}

	public void createGuideLevelInfo(GuideLevelInfo guideLevelInfo) {
		guideLevelInfoMapper.createGuideLevelInfo(guideLevelInfo);
	}

	public void createGuideLevelInfoSelective(GuideLevelInfo guideLevelInfo) {
		guideLevelInfoMapper.createGuideLevelInfoSelective(guideLevelInfo);
	}

	public void createGuideLevelInfoBitch(List<GuideLevelInfo> guideLevelInfoList) {
		for (GuideLevelInfo guideLevelInfo : guideLevelInfoList) {
			guideLevelInfoMapper.createGuideLevelInfo(guideLevelInfo);
		}
	}

	public void updateGuideLevelInfo(GuideLevelInfo guideLevelInfo) {
		guideLevelInfoMapper.updateGuideLevelInfo(guideLevelInfo);
	}

	public void updateGuideLevelInfoBitch(List<GuideLevelInfo> guideLevelInfoList) {
		for (GuideLevelInfo guideLevelInfo : guideLevelInfoList) {
			guideLevelInfoMapper.updateGuideLevelInfo(guideLevelInfo);
		}
	}

	public void updateGuideLevelInfoSelective(GuideLevelInfo guideLevelInfo) {
		guideLevelInfoMapper.updateGuideLevelInfoSelective(guideLevelInfo);
	}

	public void updateGuideLevelInfoSelectiveBitch(List<GuideLevelInfo> guideLevelInfoList) {
		for (GuideLevelInfo guideLevelInfo : guideLevelInfoList) {
			guideLevelInfoMapper.updateGuideLevelInfoSelective(guideLevelInfo);
		}
	}

	public void deleteGuideLevelInfo(Integer id) {
		guideLevelInfoMapper.deleteGuideLevelInfo(id);
	}

	public void deleteGuideLevelInfoBitch(List<Integer> idList) {
		for (Integer id : idList) {
			guideLevelInfoMapper.deleteGuideLevelInfo(id);
		}
	}

}