package com.mlx.guide.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.dao.AuthorityToResourceMapper;
import com.mlx.guide.entity.AuthorityToResource;
import com.mlx.guide.entity.Resource;

/**
 *  资源与权限关系service
 * @author QiQi-04-PC
 *
 */
@Service
@Transactional
public class AuthorityToResourceService {

	@Autowired
	private AuthorityToResourceMapper authorityToResourceMapper;
 
	public int deleteByPrimaryKey(Long id) {
		return authorityToResourceMapper.deleteByPrimaryKey(id);
	}
 
	public int insert(AuthorityToResource record) {
		return authorityToResourceMapper.insert(record);
	}

	public int insertSelective(AuthorityToResource record) {
		return authorityToResourceMapper.insertSelective(record);
	}

 
	public AuthorityToResource selectByPrimaryKey(Long id) {
		return authorityToResourceMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(AuthorityToResource record) {
		return authorityToResourceMapper.updateByPrimaryKeySelective(record);
	}
 
	public int updateByPrimaryKey(AuthorityToResource record) {
		return authorityToResourceMapper.updateByPrimaryKey(record);
	}
	
	public List<AuthorityToResource> selectByExample(AuthorityToResource authorityToResource){
		return authorityToResourceMapper.selectByExample(authorityToResource);
	}
	public PageList<AuthorityToResource> selectByExample(AuthorityToResource authorityToResource,PageBounds pageBounds){
		return authorityToResourceMapper.selectByExample(authorityToResource,pageBounds);
	}
    public int batInsert(Map<String, Object> params){
    	return authorityToResourceMapper.batInsert(params);
    };
    public int deleteBySelective(AuthorityToResource authorityToResource){
    	return authorityToResourceMapper.deleteBySelective(authorityToResource);
    	
    };
    public   PageList<Resource> selectResourceByExample(Integer authorityId, PageBounds pageBounds){
    	return authorityToResourceMapper.selectResourceByExample(authorityId, pageBounds);
    	
    }

	
}
