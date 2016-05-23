package com.mlx.guide.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.SysParam;

public interface SysParamMapper {

	public List<SysParam> getSysParamList();

	public PageList<SysParam> getSysParamList(PageBounds pageBounds);

	public List<SysParam> getSysParamPageList(SysParam sysParam);

	public PageList<SysParam> getSysParamPageList(SysParam sysParam, PageBounds pageBounds);

	public List<SysParam> getSysParamPageListByMap(Map<String, Object> map);

	public PageList<SysParam> getSysParamPageListByMap(Map<String, Object> map, PageBounds pageBounds);

	public SysParam getSysParamByPrimaryKey(Integer id);

	public void createSysParam(SysParam sysParam);

	public void createSysParamSelective(SysParam sysParam);

	public void updateSysParam(SysParam sysParam);

	public void updateSysParamSelective(SysParam sysParam);

	public void deleteSysParam(Integer id);
}