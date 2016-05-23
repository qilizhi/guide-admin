package com.mlx.guide.dao;

import com.mlx.guide.entity.UserCollect;

public interface UserCollectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserCollect record);

    int insertSelective(UserCollect record);

    UserCollect selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserCollect record);

    int updateByPrimaryKey(UserCollect record);
}