package com.mlx.guide.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.GuideLineDatePrice;

public interface GuideLineDatePriceMapper {

	public List<GuideLineDatePrice> getGuideLineDatePriceList();

	public PageList<GuideLineDatePrice> getGuideLineDatePriceList(PageBounds pageBounds);

	public List<GuideLineDatePrice> getGuideLineDatePricePageList(GuideLineDatePrice guideLineDatePrice);

	public PageList<GuideLineDatePrice> getGuideLineDatePricePageList(GuideLineDatePrice guideLineDatePrice,
			PageBounds pageBounds);

	public List<GuideLineDatePrice> getGuideLineDatePricePageListByMap(Map<String, Object> map);

	public PageList<GuideLineDatePrice> getGuideLineDatePricePageListByMap(Map<String, Object> map,
			PageBounds pageBounds);

	public GuideLineDatePrice getGuideLineDatePriceByPrimaryKey(Integer id);

	public List<GuideLineDatePrice> getGuideLineDatePriceByLineNo(String lineNo);//根据线路编号获取对应的价格表

	public void createGuideLineDatePrice(GuideLineDatePrice guideLineDatePrice);

	public void createGuideLineDatePriceSelective(GuideLineDatePrice guideLineDatePrice);

	public void updateGuideLineDatePrice(GuideLineDatePrice guideLineDatePrice);

	public void updateGuideLineDatePriceSelective(GuideLineDatePrice guideLineDatePrice);

	public void deleteGuideLineDatePrice(Integer id);
	
	public void deleteGuideLineDatePriceByDate(Map<String, Object> map);

	public void deleteGuideLineDatePriceByLineNo(String lineNo);
	//根据lineNo查询开始日期和结束日期
	Map<String,Date> getLineDateByLineNo(String lineNo);
	
}