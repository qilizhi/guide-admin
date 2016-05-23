package com.mlx.guide.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.dao.SysBizLogMapper;
import com.mlx.guide.entity.SysBizLog;

@Service
@Transactional
public class SysBizLogService {

	@Autowired
	private SysBizLogMapper sysBizLogMapper;

	public List<SysBizLog> getSysBizLogList() {
		return sysBizLogMapper.getSysBizLogList();
	}

	public PageList<SysBizLog> getSysBizLogList(PageBounds pageBounds) {
		return sysBizLogMapper.getSysBizLogList(pageBounds);
	}

	public List<SysBizLog> getSysBizLogPageList(SysBizLog sysBizLog) {
		return sysBizLogMapper.getSysBizLogPageList(sysBizLog);
	}

	public PageList<SysBizLog> getSysBizLogPageList(SysBizLog sysBizLog, PageBounds pageBounds) {
		return sysBizLogMapper.getSysBizLogPageList(sysBizLog, pageBounds);
	}

	public List<SysBizLog> getSysBizLogPageListByMap(Map<String, Object> map) {
		return sysBizLogMapper.getSysBizLogPageListByMap(map);
	}

	public PageList<SysBizLog> getSysBizLogPageListByMap(Map<String, Object> map, PageBounds pageBounds) {
		return sysBizLogMapper.getSysBizLogPageListByMap(map, pageBounds);
	}

	public SysBizLog getSysBizLogByPrimaryKey(Integer id) {
		return sysBizLogMapper.getSysBizLogByPrimaryKey(id);
	}

	public void createSysBizLog(SysBizLog sysBizLog) {
		sysBizLogMapper.createSysBizLog(sysBizLog);
	}

	public void createSysBizLogSelective(SysBizLog sysBizLog) {
		sysBizLogMapper.createSysBizLogSelective(sysBizLog);
	}

	public void createSysBizLogBitch(List<SysBizLog> sysBizLogList) {
		for (SysBizLog sysBizLog : sysBizLogList) {
			sysBizLogMapper.createSysBizLog(sysBizLog);
		}
	}

	public void updateSysBizLog(SysBizLog sysBizLog) {
		sysBizLogMapper.updateSysBizLog(sysBizLog);
	}

	public void updateSysBizLogBitch(List<SysBizLog> sysBizLogList) {
		for (SysBizLog sysBizLog : sysBizLogList) {
			sysBizLogMapper.updateSysBizLog(sysBizLog);
		}
	}

	public void updateSysBizLogSelective(SysBizLog sysBizLog) {
		sysBizLogMapper.updateSysBizLogSelective(sysBizLog);
	}

	public void updateSysBizLogSelectiveBitch(List<SysBizLog> sysBizLogList) {
		for (SysBizLog sysBizLog : sysBizLogList) {
			sysBizLogMapper.updateSysBizLogSelective(sysBizLog);
		}
	}

	public void deleteSysBizLog(Integer id) {
		sysBizLogMapper.deleteSysBizLog(id);
	}

	public void deleteSysBizLogBitch(List<Integer> idList) {
		for (Integer id : idList) {
			sysBizLogMapper.deleteSysBizLog(id);
		}
	}

}