package com.mlx.guide.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mlx.guide.dao.RoleMapper;
import com.mlx.guide.dao.UserToRoleMapper;
import com.mlx.guide.entity.Role;
import com.mlx.guide.entity.UserToRole;
import com.mlx.guide.model.State;
import com.mlx.guide.model.Tree;
@Service
@Transactional
public class UserToRoleService  {

	@Autowired
	private UserToRoleMapper userToRoleMapper;
	@Autowired
	private RoleMapper roleMapper;
 
	public int deleteByPrimaryKey(Integer id) {
		return userToRoleMapper.deleteByPrimaryKey(id);
	}

 
	public int insert(UserToRole record) {
		return userToRoleMapper.insert(record);
	}

 
	public int insertSelective(UserToRole record) {
		return userToRoleMapper.insertSelective(record);
	}

 
	public UserToRole selectByPrimaryKey(Integer id) {
		return userToRoleMapper.selectByPrimaryKey(id);
	}

 
	public int updateByPrimaryKeySelective(UserToRole record) {
		return userToRoleMapper.updateByPrimaryKeySelective(record);
	}

 
	public int updateByPrimaryKey(UserToRole record) {
		return userToRoleMapper.updateByPrimaryKey(record);
	}

 
	public List<UserToRole> selectByUserNo(String userNo) {
		return userToRoleMapper.selectByUserNo(userNo);
	}

 
	public void batDelete(Map<String, Object> params) {
		userToRoleMapper.batDelete(params);
		
	}

 
	public void batInsert(Map<String, Object> params) {
		userToRoleMapper.batInsert(params);
		
	}
	
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
				List<Role> roles = roleMapper.selectByParentId(aut.getId());
				if (roles != null && roles.size() > 0) {
					AT.setChildren(getTree(roles));
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
					st.setOpened(true);
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
