package com.mlx.guide.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.dao.SysParamMapper;
import com.mlx.guide.entity.SysParam;

@Service
@Transactional
public class SysParamService {

	@Autowired
	private SysParamMapper sysParamMapper;

	public List<SysParam> getSysParamList() {
		return sysParamMapper.getSysParamList();
	}

	public PageList<SysParam> getSysParamList(PageBounds pageBounds) {
		return sysParamMapper.getSysParamList(pageBounds);
	}

	public List<SysParam> getSysParamPageList(SysParam sysParam) {
		return sysParamMapper.getSysParamPageList(sysParam);
	}

	public PageList<SysParam> getSysParamPageList(SysParam sysParam, PageBounds pageBounds) {
		return sysParamMapper.getSysParamPageList(sysParam, pageBounds);
	}

	public List<SysParam> getSysParamPageListByMap(Map<String, Object> map) {
		return sysParamMapper.getSysParamPageListByMap(map);
	}

	public PageList<SysParam> getSysParamPageListByMap(Map<String, Object> map, PageBounds pageBounds) {
		return sysParamMapper.getSysParamPageListByMap(map, pageBounds);
	}

	public SysParam getSysParamByPrimaryKey(Integer id) {
		return sysParamMapper.getSysParamByPrimaryKey(id);
	}

	public void createSysParam(SysParam sysParam) {
		sysParamMapper.createSysParam(sysParam);
	}

	public void createSysParamSelective(SysParam sysParam) {
		sysParamMapper.createSysParamSelective(sysParam);
	}

	public void createSysParamBitch(List<SysParam> sysParamList) {
		for (SysParam sysParam : sysParamList) {
			sysParamMapper.createSysParam(sysParam);
		}
	}

	public void updateSysParam(SysParam sysParam) {
		sysParamMapper.updateSysParam(sysParam);
	}

	public void updateSysParamBitch(List<SysParam> sysParamList) {
		for (SysParam sysParam : sysParamList) {
			sysParamMapper.updateSysParam(sysParam);
		}
	}

	public void updateSysParamSelective(SysParam sysParam) {
		sysParamMapper.updateSysParamSelective(sysParam);
	}

	public void updateSysParamSelectiveBitch(List<SysParam> sysParamList) {
		for (SysParam sysParam : sysParamList) {
			sysParamMapper.updateSysParamSelective(sysParam);
		}
	}

	public void deleteSysParam(Integer id) {
		sysParamMapper.deleteSysParam(id);
	}

	public void deleteSysParamBitch(List<Integer> idList) {
		for (Integer id : idList) {
			sysParamMapper.deleteSysParam(id);
		}
	}

}