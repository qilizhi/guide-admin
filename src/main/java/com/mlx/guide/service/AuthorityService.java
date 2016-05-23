package com.mlx.guide.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.mlx.guide.dao.AuthorityMapper;
import com.mlx.guide.entity.Authority;
import com.mlx.guide.entity.RoleToAuthority;
import com.mlx.guide.model.State;
import com.mlx.guide.model.Tree;

@Service
@CacheConfig(cacheNames="mlx",keyGenerator="keyGenerator")
@Transactional
public class AuthorityService {

	@Autowired
	private AuthorityMapper authorityMapper;
	

	@CacheEvict( allEntries = true)
	public int deleteByPrimaryKey(Integer id) {
		return authorityMapper.deleteByPrimaryKey(id);
	}

	
	 @CachePut
	public int insert(Authority record) {
		return authorityMapper.insert(record);
	}


	 @CachePut
	public int insertSelective(Authority record) {
		return authorityMapper.insertSelective(record);
	}


	 @Cacheable(condition="#id != null")
	public Authority selectByPrimaryKey(Integer id) {
		return authorityMapper.selectByPrimaryKey(id);
	}


	@CacheEvict(allEntries = true)
	public int updateByPrimaryKeySelective(Authority record) {
		return authorityMapper.updateByPrimaryKeySelective(record);
	}


	@CacheEvict(allEntries = true)
	public int updateByPrimaryKey(Authority record) {
		return authorityMapper.updateByPrimaryKey(record);
	}


	@Cacheable(condition="#parentId != null" )
	public List<Authority> selectByParentId(Integer parentId) {
		return authorityMapper.selectByParentId(parentId);
	}


	@Cacheable
	public List<Authority> selectFirstParentId() {
		return authorityMapper.selectFirstParentId();
	}
	

	@Cacheable
	public List<Authority> list(Authority auth,PageBounds bounds) {
		return authorityMapper.list(auth,bounds);
	}
	
	public List<Authority> getAuhtoritysByUserNo(String userNo){
		
		return authorityMapper.getAuhtoritysByUserNo(userNo);
	}
	
	/**
	 * 递 归生成结构树
	 * 
	 */

	public List<Tree> getTree(List<Authority> authList) {
		List<Tree> auTrees = new ArrayList<Tree>();
		for (Authority aut : authList) {
			Tree AT = new Tree();
			if (aut != null && aut.getId() != null) {
				AT.setId(aut.getId());
				AT.setText(aut.getName());
				AT.setParentId(aut.getParentId());
				// 查询 子Node
				List<Authority> authoritys = authorityMapper.selectByParentId(aut.getId());
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
		List<Authority> authoritys = authorityMapper.selectFirstParentId();
		if (authoritys != null && authoritys.size() > 0) {
			ATs = getTree(authoritys);
		}
		return ATs;

	}
	
	/**
	 * 递归标识已受权的树
	 * 
	 * 
	 */

	public List<Tree> tagTree(List<RoleToAuthority> roleToAuthoritys, List<Tree> authorityTrees) {
		List<Tree> ATs = new ArrayList<Tree>();
		for (Tree at : authorityTrees) {
			if(at!=null){
			for (RoleToAuthority RT : roleToAuthoritys) {
				if (RT!=null&&RT.getAuthorityId()!=null&&at.getId()!=null&&RT.getAuthorityId() == at.getId()) {
					State st=new State();
					/*st.setSelected(true);*/
					st.setOpened(true);
					st.setChecked(true);
					at.setState(st);;
					roleToAuthoritys.remove(at);
				}
			}}
			if(at.getChildren()!=null&&at.getChildren().size()>0){
			at.setChildren(tagTree(roleToAuthoritys, at.getChildren()));}
			ATs.add(at);
		}

		return ATs;
	}



	

}
