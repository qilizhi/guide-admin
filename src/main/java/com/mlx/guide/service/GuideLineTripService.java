package com.mlx.guide.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.dao.GuideLineTripMapper;
import com.mlx.guide.entity.GuideLineTrip;

@Service
@Transactional
public class GuideLineTripService {

	@Autowired
	private GuideLineTripMapper guideLineTripMapper;

	public int deleteByPrimaryKey(Long id) {
		return guideLineTripMapper.deleteByPrimaryKey(id);
	}

	public int insert(GuideLineTrip record) {
		return guideLineTripMapper.insert(record);
	}

	public int insertSelective(GuideLineTrip record) {
		return guideLineTripMapper.insertSelective(record);
	}

	public GuideLineTrip selectByPrimaryKey(Long id) {
		return guideLineTripMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(GuideLineTrip record) {
		return guideLineTripMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(GuideLineTrip record) {
		return guideLineTripMapper.updateByPrimaryKey(record);
	}

	public List<GuideLineTrip> getGuideLineTripPageList(GuideLineTrip record) {
		return guideLineTripMapper.getGuideLineTripPageList(record);
	}

	public PageList<GuideLineTrip> getGuideLineTripPageList(GuideLineTrip record, PageBounds pageBounds) {
		return guideLineTripMapper.getGuideLineTripPageList(record, pageBounds);
	}

	public void updateBitchSelective(List<GuideLineTrip> ls) {
		for (GuideLineTrip guideLineTrip : ls) {
			if(guideLineTrip.getId() != null && guideLineTrip.getId() > 0){
				guideLineTrip.setUpdateTime(new Date());
				guideLineTripMapper.updateByPrimaryKeySelective(guideLineTrip);
			}else{
				guideLineTripMapper.insertSelective(guideLineTrip);
			}
		}
	}

	public void deleteGuideLineTripByLineNo(String lineNo){
		guideLineTripMapper.deleteGuideLineTripByLineNo(lineNo);
	}
}
