package com.mlx.guide.dao;

import java.util.List;

import com.mlx.guide.entity.EmUser;

public interface EmUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EmUser record);

    int insertSelective(EmUser record);

    EmUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EmUser record);

    int updateByPrimaryKey(EmUser record);

	List<EmUser> listByExample(EmUser record);
}