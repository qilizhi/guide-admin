package com.mlx.guide.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.constant.EAuditStatus;
import com.mlx.guide.constant.EFlag;
import com.mlx.guide.dao.GuideStrategyMapper;
import com.mlx.guide.entity.GuideStrategy;

@Service
@Transactional
public class GuideStrategyService {

    @Autowired
	private GuideStrategyMapper guideStrategyMapper;
	
//	public List<GuideStrategy> getGuideStrategyList(){
//		return guideStrategyMapper.getGuideStrategyList();
//	}
//	
//	public PageList<GuideStrategy> getGuideStrategyList(PageBounds pageBounds){
//		return guideStrategyMapper.getGuideStrategyList(pageBounds);
//	}
	
	public List<GuideStrategy> getGuideStrategyPageList(GuideStrategy guideStrategy){
		guideStrategy.setFlag( EFlag.VALID.getId() );
		return guideStrategyMapper.getGuideStrategyPageList(guideStrategy);
	}
	
	public PageList<GuideStrategy> getGuideStrategyPageList(GuideStrategy guideStrategy, PageBounds pageBounds){
		guideStrategy.setFlag( EFlag.VALID.getId() );
		return guideStrategyMapper.getGuideStrategyPageList(guideStrategy,pageBounds);
	}
	
	public List<GuideStrategy> getGuideStrategyPageListByMap(Map<String, Object> map){
		map.put( "flag", EFlag.VALID.getId() );
		return guideStrategyMapper.getGuideStrategyPageListByMap(map);
	}
	
	public PageList<GuideStrategy> getGuideStrategyPageListByMap(Map<String, Object> map, PageBounds pageBounds){
		map.put( "flag", EFlag.VALID.getId() );
		return guideStrategyMapper.getGuideStrategyPageListByMap(map,pageBounds);
	}
	
	public GuideStrategy getGuideStrategyByPrimaryKey(Integer id){
		return guideStrategyMapper.getGuideStrategyByPrimaryKey(id);
	}
	
	public void createGuideStrategy(GuideStrategy guideStrategy){
		guideStrategyMapper.createGuideStrategy(guideStrategy);
	}
	
	public void createGuideStrategySelective(GuideStrategy guideStrategy) {
		guideStrategyMapper.createGuideStrategySelective(guideStrategy);
	}	
	
	public void createGuideStrategyBitch(List<GuideStrategy> guideStrategyList){
		for( GuideStrategy guideStrategy : guideStrategyList ) {
			guideStrategyMapper.createGuideStrategy(guideStrategy);
		}
	}
	
	public void updateGuideStrategy(GuideStrategy guideStrategy){
		guideStrategyMapper.updateGuideStrategy(guideStrategy);
	}
	
	public void updateGuideStrategyBitch(List<GuideStrategy> guideStrategyList){
	    for( GuideStrategy guideStrategy : guideStrategyList ) {
		    guideStrategyMapper.updateGuideStrategy(guideStrategy);
		}
	}
	
	public int updateGuideStrategySelective(GuideStrategy guideStrategy){
	  return  guideStrategyMapper.updateGuideStrategySelective(guideStrategy);
	}
	
	public void updateGuideStrategySelectiveBitch(List<GuideStrategy> guideStrategyList){
	    for( GuideStrategy guideStrategy : guideStrategyList ) {
		    guideStrategyMapper.updateGuideStrategySelective(guideStrategy);
		}
	}
	
	public int deleteGuideStrategy(Integer id){
		GuideStrategy guideStrategy = new GuideStrategy();
		guideStrategy.setId( id );
		guideStrategy.setFlag( EFlag.INVALID.getId() );
		return guideStrategyMapper.updateGuideStrategySelective( guideStrategy );
	}
	
	public void deleteGuideStrategyBitch(List<Integer> idList){
		for( Integer id : idList ) {
			deleteGuideStrategy(id);
		}
	}

	/**
	 * 审核
	 * @param auditRemark
	 * @param auditStatus
	 * @param ids
	 */
	public void updateAuditStatusBitch( String auditRemark, int auditStatus , int... ids ){
		EAuditStatus eAuditStatus = EAuditStatus.getCategory( auditStatus );
	    for( int id : ids ) {
			GuideStrategy guideStrategy = new GuideStrategy();
			guideStrategy.setId( id );
			guideStrategy.setAuditRemark( auditRemark );
			guideStrategy.setAuditStatus( eAuditStatus.getId() );
			guideStrategy.setAuditTime( new Date() );
			guideStrategyMapper.updateGuideStrategySelective( guideStrategy );
		}
	}
	
	public List<GuideStrategy> getGuideStrategyRankingList(PageBounds pageBounds){
		return guideStrategyMapper.getGuideStrategyRankingList( pageBounds );
	}
}