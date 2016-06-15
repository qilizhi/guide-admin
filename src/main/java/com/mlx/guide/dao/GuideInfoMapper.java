package com.mlx.guide.dao;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.GuideInfo;
import com.mlx.guide.model.GuideInfoModel;

public interface GuideInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GuideInfo record);

    int insertSelective(GuideInfo record);

    GuideInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GuideInfo record);

    int updateByPrimaryKey(GuideInfo record);
    
    GuideInfo getGuideInfoByUserNo(String userNo);

    
	PageList<GuideInfoModel> getGuideInfoPageList(
			GuideInfoModel guideInfoModel, PageBounds pageBounds);

	List<GuideInfo> getGuideInfoList(GuideInfo guideInfo);

	Long countByDate(Integer month);

	/**
	 * 分页没通过审核状态的导游
	 * @param guideInfoModel
	 * @param pageBounds
	 * @return
	 */
	PageList<GuideInfoModel> getPageListByAuthstr(
			GuideInfoModel guideInfoModel, PageBounds pageBounds);
	PageList<GuideInfo> getPageListByAuthstr(GuideInfoModel guideInfoModel);

	PageList<GuideInfo> getGuideInfoList(GuideInfo guideInfo, PageBounds pb);
}