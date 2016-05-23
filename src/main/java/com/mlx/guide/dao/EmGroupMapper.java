package com.mlx.guide.dao;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.EmGroup;

public interface EmGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EmGroup record);

    int insertSelective(EmGroup record);

    EmGroup selectByPrimaryKey(Long id);
    PageList<EmGroup> listBySelective(EmGroup record, PageBounds pageBounds);
    int updateByPrimaryKeySelective(EmGroup record);

    int updateByPrimaryKey(EmGroup record);

	List<EmGroup> listBySelective(EmGroup record);

	void batDeleteByPrimaryKey(String[] ids);
}