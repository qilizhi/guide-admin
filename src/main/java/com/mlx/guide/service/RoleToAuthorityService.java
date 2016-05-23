package com.mlx.guide.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mlx.guide.dao.RoleToAuthorityMapper;
import com.mlx.guide.entity.RoleToAuthority;

@Service
@CacheConfig(cacheNames = "mlx", keyGenerator = "keyGenerator")
public class RoleToAuthorityService {
	@Autowired

	private RoleToAuthorityMapper roleToAuthorityMapper;

 
	@CacheEvict(allEntries = true)
	public int deleteByPrimaryKey(Integer id) {
		return roleToAuthorityMapper.deleteByPrimaryKey(id);
	}

 
	@CachePut
	public int insert(RoleToAuthority record) {
		// TODO Auto-generated method stub
		return roleToAuthorityMapper.insert(record);
	}

 
	@CachePut
	public int insertSelective(RoleToAuthority record) {
		// TODO Auto-generated method stub
		return roleToAuthorityMapper.insertSelective(record);
	}

 
	@Cacheable(condition = "#id != null")
	public RoleToAuthority selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return roleToAuthorityMapper.selectByPrimaryKey(id);
	}

 
	@CacheEvict(allEntries = true)
	public int updateByPrimaryKeySelective(RoleToAuthority record) {
		// TODO Auto-generated method stub
		return roleToAuthorityMapper.updateByPrimaryKeySelective(record);
	}

 
	@CacheEvict(allEntries = true)
	public int updateByPrimaryKey(RoleToAuthority record) {
		// TODO Auto-generated method stub
		return roleToAuthorityMapper.updateByPrimaryKey(record);
	}

 
	@CacheEvict(value = "mlx", allEntries = true)
	public int deleteByRoleId(Integer roleId) {

		return roleToAuthorityMapper.deleteByRoleId(roleId);
	}
	@CacheEvict(value = "mlx", allEntries = true)
   public  int batInsert(Map<String, Object> params){    	
    	return roleToAuthorityMapper.batInsert(params);
    };
	/**
	 * 给角色授权
	 * 
	 */

 
	@Transactional
	public void auth(Integer roleId, String authorityIds) {

		roleToAuthorityMapper.deleteByRoleId(roleId);
		String[] authIds = authorityIds.split(",");
		for (String authid : authIds) {
			if (authid != null || "".equals(authid)) {
				RoleToAuthority record = new RoleToAuthority();
				record.setRoleId(roleId);
				record.setAuthorityId(Integer.parseInt(authid));
				roleToAuthorityMapper.insertSelective(record);
			}
		}

	}

 
	@Cacheable(condition = "#id != null")
	public List<RoleToAuthority> selectByRoleId(Integer roleId) {

		return roleToAuthorityMapper.selectByRoleId(roleId);
	}


	public void deleteByRoleToAuthority(RoleToAuthority rta) {
			roleToAuthorityMapper.deleteByRoleToAuthority(rta);
		
	}
	public int batDelete(Map<String, Object> params){
		return roleToAuthorityMapper.batDelete(params);
	};

}
