package com.mlx.guide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.dao.GuideIntroMapper;
import com.mlx.guide.entity.GuideIntro;
import com.mlx.guide.model.GuideInfoModel;
import com.mlx.guide.model.GuideIntroModel;

@Service
@Transactional
public class GuideIntroService {

	@Autowired
	private GuideIntroMapper guideIntroMapper;
	
    public int deleteByPrimaryKey(Long id){
    	return guideIntroMapper.deleteByPrimaryKey(id);
    }

    public int insert(GuideIntro record){
    	return guideIntroMapper.insert(record);
    }

    public int insertSelective(GuideIntro record){
    	return guideIntroMapper.insertSelective(record);
    }

    public GuideIntro selectByPrimaryKey(Long id){
    	return guideIntroMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(GuideIntro record){
    	return guideIntroMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKeyWithBLOBs(GuideIntro record){
    	return guideIntroMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    public int updateByPrimaryKey(GuideIntro record){
    	return guideIntroMapper.updateByPrimaryKey(record);
    }
    
    public GuideIntro getGuideIntroByUserNo(String userNo){
    	return guideIntroMapper.getGuideIntroByUserNo(userNo);
    }

	public PageList<GuideInfoModel> getGuideIntroPageList(
			GuideIntroModel guideIntroModel, PageBounds pageBounds) {
		return guideIntroMapper.getGuideIntroPageList(guideIntroModel,pageBounds);
	}
}
