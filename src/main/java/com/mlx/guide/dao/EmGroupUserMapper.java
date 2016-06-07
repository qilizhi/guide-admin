package com.mlx.guide.dao;

import com.mlx.guide.entity.EmGroupUser;

public interface EmGroupUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EmGroupUser record);

    int insertSelective(EmGroupUser record);

    EmGroupUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EmGroupUser record);

    int updateByPrimaryKey(EmGroupUser record);

	EmGroupUser selectByUserNo(String userNo);
}