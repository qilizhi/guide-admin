package com.mlx.guide.dao;

import com.mlx.guide.entity.Commend;

public interface CommendMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Commend record);

    int insertSelective(Commend record);

    Commend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Commend record);

    int updateByPrimaryKey(Commend record);
}