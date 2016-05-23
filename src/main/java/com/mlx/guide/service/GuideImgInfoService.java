package com.mlx.guide.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.dao.GuideImgInfoMapper;
import com.mlx.guide.entity.GuideImgInfo;

@Service
@Transactional
public class GuideImgInfoService {

    @Autowired
	private GuideImgInfoMapper guideImgInfoMapper;
	
	public List<GuideImgInfo> getGuideImgInfoList(){
		return guideImgInfoMapper.getGuideImgInfoList();
	}
	
	public PageList<GuideImgInfo> getGuideImgInfoList(PageBounds pageBounds){
		return guideImgInfoMapper.getGuideImgInfoList(pageBounds);
	}
	
	public List<GuideImgInfo> getGuideImgInfoPageList(GuideImgInfo guideImgInfo){
		return guideImgInfoMapper.getGuideImgInfoPageList(guideImgInfo);
	}
	
	public PageList<GuideImgInfo> getGuideImgInfoPageList(GuideImgInfo guideImgInfo, PageBounds pageBounds){
		return guideImgInfoMapper.getGuideImgInfoPageList(guideImgInfo,pageBounds);
	}
	
	public List<GuideImgInfo> getGuideImgInfoPageListByMap(Map<String, Object> map){
		return guideImgInfoMapper.getGuideImgInfoPageListByMap(map);
	}
	
	public PageList<GuideImgInfo> getGuideImgInfoPageListByMap(Map<String, Object> map, PageBounds pageBounds){
		return guideImgInfoMapper.getGuideImgInfoPageListByMap(map,pageBounds);
	}
	
	public GuideImgInfo getGuideImgInfoByPrimaryKey(Integer id){
		return guideImgInfoMapper.getGuideImgInfoByPrimaryKey(id);
	}
	
	public void createGuideImgInfo(GuideImgInfo guideImgInfo){
		guideImgInfoMapper.createGuideImgInfo(guideImgInfo);
	}

	public void createGuideImgInfoSelective(GuideImgInfo guideImgInfo){
		guideImgInfoMapper.createGuideImgInfoSelective(guideImgInfo);
	}
	public void createGuideImgInfoBitch(List<GuideImgInfo> guideImgInfoList){
		for( GuideImgInfo guideImgInfo : guideImgInfoList ) {
			guideImgInfoMapper.createGuideImgInfo(guideImgInfo);
		}
	}
	
	public void updateGuideImgInfo(GuideImgInfo guideImgInfo){
		guideImgInfoMapper.updateGuideImgInfo(guideImgInfo);
	}
	
	public void updateGuideImgInfoBitch(List<GuideImgInfo> guideImgInfoList){
	    for( GuideImgInfo guideImgInfo : guideImgInfoList ) {
		    guideImgInfoMapper.updateGuideImgInfo(guideImgInfo);
		}
	}
	
	public void updateGuideImgInfoSelective(GuideImgInfo guideImgInfo){
	    guideImgInfoMapper.updateGuideImgInfoSelective(guideImgInfo);
	}
	
	public void updateGuideImgInfoSelectiveBitch(List<GuideImgInfo> guideImgInfoList){
	    for( GuideImgInfo guideImgInfo : guideImgInfoList ) {
		    guideImgInfoMapper.updateGuideImgInfoSelective(guideImgInfo);
		}
	}
	
	public void deleteGuideImgInfo(Integer id){
		guideImgInfoMapper.deleteGuideImgInfo(id);
	}
	
	public void deleteGuideImgInfoBitch(List<Integer> idList){
		for( Integer id : idList ) {
			guideImgInfoMapper.deleteGuideImgInfo(id);
		}
	}

}