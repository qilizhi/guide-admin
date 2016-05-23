package com.mlx.guide.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.GuideIntro;
import com.mlx.guide.model.GuideInfoModel;
import com.mlx.guide.model.GuideIntroModel;

public interface GuideIntroMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GuideIntro record);

    int insertSelective(GuideIntro record);

    GuideIntro selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GuideIntro record);

    int updateByPrimaryKeyWithBLOBs(GuideIntro record);

    int updateByPrimaryKey(GuideIntro record);
    
    GuideIntro getGuideIntroByUserNo(String userNo);

	PageList<GuideInfoModel> getGuideIntroPageList(
			GuideIntroModel guideIntroModel, PageBounds pageBounds);
}