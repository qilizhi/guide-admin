package com.mlx.guide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlx.guide.dao.EmGroupUserMapper;
import com.mlx.guide.entity.EmGroupUser;

@Service
public class EmGroupUserService implements EmGroupUserMapper {

	@Autowired
	private EmGroupUserMapper emGroupUserMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return emGroupUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(EmGroupUser record) {
		return emGroupUserMapper.insert(record);
	}

	@Override
	public int insertSelective(EmGroupUser record) {
		return emGroupUserMapper.insertSelective(record);
	}

	@Override
	public EmGroupUser selectByPrimaryKey(Long id) {
		return emGroupUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(EmGroupUser record) {
		// TODO Auto-generated method stub
		return emGroupUserMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(EmGroupUser record) {
		return emGroupUserMapper.updateByPrimaryKey(record);
	}

}
