package com.mlx.guide.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.WxTwoDimensionalCode;

public interface WxTwoDimensionalCodeMapper {

	public List<WxTwoDimensionalCode> getWxTwoDimensionalCodeList();

	public PageList<WxTwoDimensionalCode> getWxTwoDimensionalCodeList(PageBounds pageBounds);

	public List<WxTwoDimensionalCode> getWxTwoDimensionalCodePageList(WxTwoDimensionalCode wxTwoDimensionalCode);

	public PageList<WxTwoDimensionalCode> getWxTwoDimensionalCodePageList(WxTwoDimensionalCode wxTwoDimensionalCode,
			PageBounds pageBounds);

	public List<WxTwoDimensionalCode> getWxTwoDimensionalCodePageListByMap(Map<String, Object> map);

	public PageList<WxTwoDimensionalCode> getWxTwoDimensionalCodePageListByMap(Map<String, Object> map,
			PageBounds pageBounds);

	public WxTwoDimensionalCode getWxTwoDimensionalCodeByPrimaryKey(Long id);

	public void createWxTwoDimensionalCode(WxTwoDimensionalCode wxTwoDimensionalCode);

	public void createWxTwoDimensionalCodeSelective(WxTwoDimensionalCode wxTwoDimensionalCode);

	public void updateWxTwoDimensionalCode(WxTwoDimensionalCode wxTwoDimensionalCode);

	public void updateWxTwoDimensionalCodeSelective(WxTwoDimensionalCode wxTwoDimensionalCode);

	public void deleteWxTwoDimensionalCode(Long id);
}