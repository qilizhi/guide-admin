package com.mlx.guide.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mlx.guide.dao.EmUserMapper;
import com.mlx.guide.entity.EmUser;

/**
 * 
 * @author QiQi-04-PC
 *
 */
@Service
@Transactional
public class EmUserService  {

	@Autowired
	private EmUserMapper emUserMapper;

	
	public int deleteByPrimaryKey(Long id) {
		return emUserMapper.deleteByPrimaryKey(id);
	}

	
	public int insert(EmUser record) {
		return emUserMapper.insert(record);
	}

	
	public int insertSelective(EmUser record) {
		return emUserMapper.insertSelective(record);
	}

	
	public EmUser selectByPrimaryKey(Long id) {
		return emUserMapper.selectByPrimaryKey(id);
	}

	
	public int updateByPrimaryKeySelective(EmUser record) {
		return emUserMapper.updateByPrimaryKeySelective(record);
	}

	
	public int updateByPrimaryKey(EmUser record) {
		return emUserMapper.updateByPrimaryKey(record);
	}
	
    public List<EmUser> listByExample(EmUser record){
    	return emUserMapper.listByExample(record);
    	
    	
    }
}
