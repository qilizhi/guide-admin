package com.mlx.guide.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mlx.guide.dao.EmGroupUserMapper;
import com.mlx.guide.entity.EmGroup;
import com.mlx.guide.entity.EmGroupUser;

@Service
public class EmGroupUserService {

	@Autowired
	private EmGroupUserMapper emGroupUserMapper;

	public int deleteByPrimaryKey(Long id) {
		return emGroupUserMapper.deleteByPrimaryKey(id);
	}

	public int insert(EmGroupUser record) {
		return emGroupUserMapper.insert(record);
	}

	public int insertSelective(EmGroupUser record) {
		return emGroupUserMapper.insertSelective(record);
	}

	public EmGroupUser selectByPrimaryKey(Long id) {
		return emGroupUserMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(EmGroupUser record) {
		// TODO Auto-generated method stub
		return emGroupUserMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(EmGroupUser record) {
		return emGroupUserMapper.updateByPrimaryKey(record);
	}

	@Transactional
	public void insertGroupUsers(List<String> users, Long group) {
		for (String user : users) {
			EmGroupUser record = new EmGroupUser();
			record.setEmUser(user);
			record.setEmGid(group);
			emGroupUserMapper.insertSelective(record);
		}

	}

	public EmGroupUser selectByUserNo(String userNo) {
		// TODO Auto-generated method stub
		return emGroupUserMapper.selectByUserNo(userNo);
	}

}
