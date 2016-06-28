package com.mlx.guide.dao;

import com.mlx.guide.entity.BeautyBus;

public interface BeautyBusMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BeautyBus record);

    int insertSelective(BeautyBus record);

    BeautyBus selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BeautyBus record);

    int updateByPrimaryKey(BeautyBus record);
}