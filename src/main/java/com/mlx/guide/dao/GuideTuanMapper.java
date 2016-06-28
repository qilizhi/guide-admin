package com.mlx.guide.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.GuideTuan;

public interface GuideTuanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GuideTuan record);

    int insertSelective(GuideTuan record);

    GuideTuan selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GuideTuan record);

    int updateByPrimaryKey(GuideTuan record);

	List<GuideTuan> getGuideTuanPageList(GuideTuan gt);

	PageList<GuideTuan> getGuideTuanPageListByMap(Map<String, Object> map, PageBounds pageBounds);

	PageList<GuideTuan> getGuideTuanPageList(GuideTuan guideTuan, PageBounds pageBounds);

	Long countByDate(Integer month);

	void deleteGuideTuanByLineNo(String goodsNo);



	List<GuideTuan> getGuideLineDatePriceByGoodsNo(String lineNo);
	//根据lineNo查询开始日期和结束日期
	Map<String,Date> getLineDateByGoodsNo(String goodsNo);

	void deleteGuideLineDatePriceByDate(Map<String, Object> map);

}