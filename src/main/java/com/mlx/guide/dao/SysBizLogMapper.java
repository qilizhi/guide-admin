package com.mlx.guide.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.SysBizLog;

public interface SysBizLogMapper {

	public List<SysBizLog> getSysBizLogList();

	public PageList<SysBizLog> getSysBizLogList(PageBounds pageBounds);

	public List<SysBizLog> getSysBizLogPageList(SysBizLog sysBizLog);

	public PageList<SysBizLog> getSysBizLogPageList(SysBizLog sysBizLog, PageBounds pageBounds);

	public List<SysBizLog> getSysBizLogPageListByMap(Map<String, Object> map);

	public PageList<SysBizLog> getSysBizLogPageListByMap(Map<String, Object> map, PageBounds pageBounds);

	public SysBizLog getSysBizLogByPrimaryKey(Integer id);

	public void createSysBizLog(SysBizLog sysBizLog);

	public void createSysBizLogSelective(SysBizLog sysBizLog);

	public void updateSysBizLog(SysBizLog sysBizLog);

	public void updateSysBizLogSelective(SysBizLog sysBizLog);

	public void deleteSysBizLog(Integer id);
}