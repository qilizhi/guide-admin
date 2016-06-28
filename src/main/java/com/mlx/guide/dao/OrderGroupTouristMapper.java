package com.mlx.guide.dao;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.OrderGroupTourist;

public interface OrderGroupTouristMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderGroupTourist record);

    int insertSelective(OrderGroupTourist record);

    OrderGroupTourist selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderGroupTourist record);

    int updateByPrimaryKey(OrderGroupTourist record);

	PageList<OrderGroupTourist> getGuideTuanGuestPageList(OrderGroupTourist record);

	PageList<OrderGroupTourist> getOrderGroupTouristPageList(OrderGroupTourist guest, PageBounds pageBounds);

	void batInsertSelective(List<OrderGroupTourist> guideTuanGuests);
}