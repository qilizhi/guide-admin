package com.mlx.guide.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.dao.ResourceMapper;
import com.mlx.guide.entity.Resource;

@Service
@Transactional
public class ResourceService {

	@Autowired
	private ResourceMapper resourceMapper;
 
	public int deleteByPrimaryKey(Integer id) {
		return resourceMapper.deleteByPrimaryKey(id);
	}

 
	public int insert(Resource record) {
		return resourceMapper.insert(record);
	}

 
	public int insertSelective(Resource record) {
		return resourceMapper.insertSelective(record);
	}

 
	public Resource selectByPrimaryKey(Integer id) {
		return resourceMapper.selectByPrimaryKey(id);
	}
 
	public int updateByPrimaryKeySelective(Resource record) {
		return resourceMapper.updateByPrimaryKeySelective(record);
		
	}
 
	public int updateByPrimaryKey(Resource record) {
		return resourceMapper.updateByPrimaryKey(record);
	}
	public PageList<Resource> getResourcePageList(Resource resource, PageBounds pageBounds){
		
		return resourceMapper.getResourcePageList(resource, pageBounds);
	}
	public List<Resource> getResourceList(Resource resource){
		
		return resourceMapper.getResourceList(resource);
	}


	public void deleteResourceBitch(String [] ids) {
		
		resourceMapper.deleteResourceBitch(ids);
		
	};
	
	public List<Resource> getResourcesByUserNo(String userNo){
		
		return resourceMapper.getResourcesByUserNo(userNo);
	}
	
	public List<Resource> getResourceByRoleId(Integer roleId){
		
		return resourceMapper.getResourceByRoleId(roleId);
	}
	
	
}
