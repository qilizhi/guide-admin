package com.mlx.guide.dao;

import com.mlx.guide.entity.GuideServicePrice;

public interface GuideServicePriceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GuideServicePrice record);

    int insertSelective(GuideServicePrice record);

    GuideServicePrice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GuideServicePrice record);

    int updateByPrimaryKey(GuideServicePrice record);
}