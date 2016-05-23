package com.mlx.guide.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.dao.GuideStrategyBrowserMapper;
import com.mlx.guide.entity.GuideStrategyBrowser;

@Service
@Transactional
public class GuideStrategyBrowserService {

    @Autowired
	private GuideStrategyBrowserMapper guideStrategyBrowserMapper;
	
	public List<GuideStrategyBrowser> getGuideStrategyBrowserList(){
		return guideStrategyBrowserMapper.getGuideStrategyBrowserList();
	}
	
	public PageList<GuideStrategyBrowser> getGuideStrategyBrowserList(PageBounds pageBounds){
		return guideStrategyBrowserMapper.getGuideStrategyBrowserList(pageBounds);
	}
	
	public List<GuideStrategyBrowser> getGuideStrategyBrowserPageList(GuideStrategyBrowser guideStrategyBrowser){
		return guideStrategyBrowserMapper.getGuideStrategyBrowserPageList(guideStrategyBrowser);
	}
	
	public PageList<GuideStrategyBrowser> getGuideStrategyBrowserPageList(GuideStrategyBrowser guideStrategyBrowser, PageBounds pageBounds){
		return guideStrategyBrowserMapper.getGuideStrategyBrowserPageList(guideStrategyBrowser,pageBounds);
	}
	
	public List<GuideStrategyBrowser> getGuideStrategyBrowserPageListByMap(Map<String, Object> map){
		return guideStrategyBrowserMapper.getGuideStrategyBrowserPageListByMap(map);
	}
	
	public PageList<GuideStrategyBrowser> getGuideStrategyBrowserPageListByMap(Map<String, Object> map, PageBounds pageBounds){
		return guideStrategyBrowserMapper.getGuideStrategyBrowserPageListByMap(map,pageBounds);
	}
	
	public GuideStrategyBrowser getGuideStrategyBrowserByPrimaryKey(Integer id){
		return guideStrategyBrowserMapper.getGuideStrategyBrowserByPrimaryKey(id);
	}
	
	public void createGuideStrategyBrowser(GuideStrategyBrowser guideStrategyBrowser){
		guideStrategyBrowserMapper.createGuideStrategyBrowser(guideStrategyBrowser);
	}

	public void createGuideStrategyBrowserSelective(GuideStrategyBrowser guideStrategyBrowser){
		guideStrategyBrowserMapper.createGuideStrategyBrowserSelective(guideStrategyBrowser);
	}
	public void createGuideStrategyBrowserBitch(List<GuideStrategyBrowser> guideStrategyBrowserList){
		for( GuideStrategyBrowser guideStrategyBrowser : guideStrategyBrowserList ) {
			guideStrategyBrowserMapper.createGuideStrategyBrowser(guideStrategyBrowser);
		}
	}
	
	public void updateGuideStrategyBrowser(GuideStrategyBrowser guideStrategyBrowser){
		guideStrategyBrowserMapper.updateGuideStrategyBrowser(guideStrategyBrowser);
	}
	
	public void updateGuideStrategyBrowserBitch(List<GuideStrategyBrowser> guideStrategyBrowserList){
	    for( GuideStrategyBrowser guideStrategyBrowser : guideStrategyBrowserList ) {
		    guideStrategyBrowserMapper.updateGuideStrategyBrowser(guideStrategyBrowser);
		}
	}
	
	public void updateGuideStrategyBrowserSelective(GuideStrategyBrowser guideStrategyBrowser){
	    guideStrategyBrowserMapper.updateGuideStrategyBrowserSelective(guideStrategyBrowser);
	}
	
	public void updateGuideStrategyBrowserSelectiveBitch(List<GuideStrategyBrowser> guideStrategyBrowserList){
	    for( GuideStrategyBrowser guideStrategyBrowser : guideStrategyBrowserList ) {
		    guideStrategyBrowserMapper.updateGuideStrategyBrowserSelective(guideStrategyBrowser);
		}
	}
	
	public void deleteGuideStrategyBrowser(Integer id){
		guideStrategyBrowserMapper.deleteGuideStrategyBrowser(id);
	}
	
	public void deleteGuideStrategyBrowserBitch(List<Integer> idList){
		for( Integer id : idList ) {
			guideStrategyBrowserMapper.deleteGuideStrategyBrowser(id);
		}
	}

	public Long countByStrategyId(Integer strategyId) {
		// TODO Auto-generated method stub
		return guideStrategyBrowserMapper.countByStrategyId(strategyId);
	}

}