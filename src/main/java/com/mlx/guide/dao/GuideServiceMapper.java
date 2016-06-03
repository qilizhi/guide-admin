package com.mlx.guide.dao;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.GuideService;

public interface GuideServiceMapper {
	int deleteByPrimaryKey(Long id);

	int insert(GuideService record);

	int insertSelective(GuideService record);

	GuideService selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(GuideService record);

	int updateByPrimaryKeyWithBLOBs(GuideService record);

	int updateByPrimaryKey(GuideService record);

	PageList<GuideService> getGuideServicePageList(GuideService guideService, PageBounds pageBounds);

	PageList<GuideService> listByExample(GuideService gs, PageBounds pageBounds);

	int batDeleteByPrimaryKeys(String[] idsArray);

	int batDelByflag(String[] idsArray);

	Long countByDate(Integer month);

	List<GuideService> getGuideServiceByUserNo(String userNo);

	GuideService getGuideServiceByServiceNo(String serviceNo);

}