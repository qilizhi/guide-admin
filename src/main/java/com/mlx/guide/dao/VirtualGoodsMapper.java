package com.mlx.guide.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.VirtualGoods;

public interface VirtualGoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VirtualGoods record);

    int insertSelective(VirtualGoods record);

    VirtualGoods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VirtualGoods record);

    int updateByPrimaryKey(VirtualGoods record);

	PageList<VirtualGoods> listByExample(VirtualGoods vGoods, PageBounds pageBounds);

	int batDelByflag(String[] idsArray);
}