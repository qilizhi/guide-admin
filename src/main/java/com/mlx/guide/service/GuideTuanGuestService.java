package com.mlx.guide.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.dao.GuideTuanGuestMapper;
import com.mlx.guide.entity.GuideTuan;
import com.mlx.guide.entity.GuideTuanGuest;

@Service
@Transactional
public class GuideTuanGuestService {

	@Autowired
	private GuideTuanGuestMapper guideTuanGuestMapper;
	
	public int deleteByPrimaryKey(Long id){
		return guideTuanGuestMapper.deleteByPrimaryKey(id);
	}

	public int insert(GuideTuanGuest record){
		return guideTuanGuestMapper.insert(record);
	}

	public int insertSelective(GuideTuanGuest record){
		return guideTuanGuestMapper.insertSelective(record);
	}

	public GuideTuanGuest selectByPrimaryKey(Long id){
		return guideTuanGuestMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(GuideTuanGuest record){
		return guideTuanGuestMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(GuideTuanGuest record){
		return guideTuanGuestMapper.updateByPrimaryKey(record);
	}

	public List<GuideTuanGuest> getGuideTuanGuestPageList(GuideTuanGuest record){
		return guideTuanGuestMapper.getGuideTuanGuestPageList(record);
	}

	public PageList<GuideTuanGuest> getGuideTuanGuestPageList(GuideTuanGuest record, PageBounds pageBounds){
		return guideTuanGuestMapper.getGuideTuanGuestPageList(record, pageBounds);
	}
}
