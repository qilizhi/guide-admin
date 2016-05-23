package com.mlx.guide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.dao.GuideServiceMapper;
import com.mlx.guide.entity.GuideService;

@Service
@Transactional
public class GuideServiceService {

	@Autowired
	private GuideServiceMapper guideServiceMapper;
	
	public int deleteByPrimaryKey(Long id){
		return guideServiceMapper.deleteByPrimaryKey(id);
	}

	public int insert(GuideService record){
		return guideServiceMapper.insert(record);
	}

	public int insertSelective(GuideService record){
		return guideServiceMapper.insertSelective(record);
	}

	public GuideService selectByPrimaryKey(Long id){
		return guideServiceMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(GuideService record){
		return guideServiceMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKeyWithBLOBs(GuideService record){
		return guideServiceMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	public int updateByPrimaryKey(GuideService record){
		return guideServiceMapper.updateByPrimaryKey(record);
	}

	public PageList<GuideService> getGuideServicePageList(GuideService guideService, PageBounds pageBounds){
		return guideServiceMapper.getGuideServicePageList(guideService, pageBounds);
	}
	public PageList<GuideService> listByExample(GuideService gs, PageBounds pageBounds){
		
		return guideServiceMapper.listByExample(gs, pageBounds);
	};
	public int batDeleteByPrimaryKeys(String[] idsArray) {
		return guideServiceMapper.batDeleteByPrimaryKeys(idsArray);
	}
	public int batDelByflag(String[] idsArray){
		
		return guideServiceMapper.batDelByflag(idsArray);
	}

	public Long countByDate(Integer month) {
		return guideServiceMapper.countByDate(month);
	};
}
