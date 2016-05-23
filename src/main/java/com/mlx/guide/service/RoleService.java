package com.mlx.guide.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.mlx.guide.dao.RoleMapper;
import com.mlx.guide.entity.Role;
import com.mlx.guide.entity.UserToRole;
import com.mlx.guide.model.State;
import com.mlx.guide.model.Tree;
@Service
@CacheConfig(cacheNames="role",keyGenerator="keyGenerator")
public class RoleService {

	@Autowired
	private RoleMapper roleMapper;
 
	@CacheEvict(allEntries = true)
	public int deleteByPrimaryKey(Integer id) {
	 return roleMapper.deleteByPrimaryKey(id);
	}

 
	@CachePut
	public int insert(Role record) {
		return roleMapper.insert(record);
	}

 
	@CachePut
	public int insertSelective(Role record) {
		return roleMapper.insertSelective(record);	
	}

 
	@Cacheable(condition="#id != null")
	public Role selectByPrimaryKey(Integer id) {
		System.out.println("查询数据库");
		return roleMapper.selectByPrimaryKey(id);
	}

 
	@CacheEvict(allEntries = true)
	public int updateByPrimaryKeySelective(Role record) {
		return roleMapper.updateByPrimaryKeySelective(record);
	}

 
	@CacheEvict(allEntries = true)
	public int updateByPrimaryKey(Role record) {
		return roleMapper.updateByPrimaryKeySelective(record);
	}

 
	@Cacheable
	public List<Role> list(Role role,PageBounds bounds) {
	return	roleMapper.list(role,bounds);
	}
	
 
	@Cacheable(condition="#parentId != null" )
	public List<Role> selectByParentId(Integer parentId) {
		return roleMapper.selectByParentId(parentId);
	}

 
	@Cacheable
	public List<Role> selectFirstParentId() {		
		return roleMapper.selectFirstParentId();
	}
	
	
	List<Role> getRolesByUserNo(String userNo){
		
		return roleMapper.getRolesByUserNo(userNo);
	};
	/**
	 * 递 归生成结构树
	 * 
	 */
	
	public List<Tree> getTree(List<Role> authList) {
		List<Tree> auTrees = new ArrayList<Tree>();
		for (Role aut : authList) {
			Tree AT = new Tree();
			if (aut != null && aut.getId() != null) {
				AT.setId(aut.getId());
				AT.setText(aut.getName());
				AT.setParentId(aut.getParentId());
				// 查询 子Node
				List<Role> authoritys = roleMapper.selectByParentId(aut.getId());
				if (authoritys != null && authoritys.size() > 0) {
					AT.setChildren(getTree(authoritys));
				}
				auTrees.add(AT);
			}

		}

		return auTrees;
	}
	
	/**
	 * 获取所有树结构
	 * 
	 */

	public List<Tree> getAllTree() {

		List<Tree> ATs = new ArrayList<Tree>();
		List<Role> roles = roleMapper.selectFirstParentId();
		if (roles != null && roles.size() > 0) {
			ATs = getTree(roles);
		}
		return ATs;

	}
	
	/**
	 * 递归标识已受权的树
	 * 
	 * 
	 */

	public List<Tree> tagTree(List<UserToRole> userToRoles, List<Tree> roleTrees) {
		List<Tree> ATs = new ArrayList<Tree>();
		for (Tree at : roleTrees) {
			if(at!=null){
			for (UserToRole RT : userToRoles) {
				if (RT!=null&&RT.getRoleId()!=null&&at.getId()!=null&&RT.getRoleId() == at.getId()) {
					State st=new State();
					st.setChecked(true);
					at.setState(st);;
					userToRoles.remove(at);
				}
			}}
			if(at.getChildren()!=null&&at.getChildren().size()>0){
			at.setChildren(tagTree(userToRoles, at.getChildren()));}
			ATs.add(at);
		}

		return ATs;
	}

	

	
	

	
}
