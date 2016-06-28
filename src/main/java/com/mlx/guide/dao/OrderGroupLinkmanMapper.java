package com.mlx.guide.dao;

import com.mlx.guide.entity.OrderGroupLinkman;

public interface OrderGroupLinkmanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderGroupLinkman record);

    int insertSelective(OrderGroupLinkman record);

    OrderGroupLinkman selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderGroupLinkman record);

    int updateByPrimaryKey(OrderGroupLinkman record);
}