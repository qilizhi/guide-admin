package com.mlx.guide.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.dao.GuideTuanMapper;
import com.mlx.guide.entity.GuideTuan;

@Service
@Transactional
public class GuideTuanService {

	@Autowired
	private GuideTuanMapper guideTuanMapper;

	public int deleteByPrimaryKey(Long id) {
		return guideTuanMapper.deleteByPrimaryKey(id);
	}

	public int insert(GuideTuan record){
		return guideTuanMapper.insert(record);
	}

	public int insertSelective(GuideTuan record){
		return guideTuanMapper.insertSelective(record);
	}

	public GuideTuan selectByPrimaryKey(Long id){
		return guideTuanMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(GuideTuan record){
		return guideTuanMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(GuideTuan record){
		return guideTuanMapper.updateByPrimaryKey(record);
	}

	public PageList<GuideTuan> getGuideTuanPageListByMap(Map<String, Object> map, PageBounds pageBounds){
		return guideTuanMapper.getGuideTuanPageListByMap(map, pageBounds);
	}

	public List<GuideTuan> getGuideTuanPageList(GuideTuan record){
    	return guideTuanMapper.getGuideTuanPageList(record);
    }
	
	public PageList<GuideTuan> getGuideTuanPageList(GuideTuan guideTuan, PageBounds pageBounds){
		return guideTuanMapper.getGuideTuanPageList(guideTuan, pageBounds);
	}

	public Long countByDate(Integer month) {
		return guideTuanMapper.countByDate(month);
	}
	
}
