package com.mlx.guide.dao;

import com.mlx.guide.entity.BeautyCardLog;

public interface BeautyCardLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BeautyCardLog record);

    int insertSelective(BeautyCardLog record);

    BeautyCardLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BeautyCardLog record);

    int updateByPrimaryKey(BeautyCardLog record);
}