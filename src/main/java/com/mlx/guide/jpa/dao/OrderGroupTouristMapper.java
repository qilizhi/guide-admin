package com.mlx.guide.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mlx.guide.jpa.entities.OrderGroupTourist;

public interface OrderGroupTouristMapper extends JpaRepository<OrderGroupTourist, Long>,JpaSpecificationExecutor<OrderGroupTourist> {
    int deleteById(Long id);

  //  int insert(OrderGroupTourist record);

   // int insertSelective(OrderGroupTourist record);

    OrderGroupTourist findOneById(Long id);

   // int updateByPrimaryKeySelective(OrderGroupTourist record);

  //  int updateByPrimaryKey(OrderGroupTourist record);

	//PageList<OrderGroupTourist> getGuideTuanGuestPageList(OrderGroupTourist record);

	//PageList<OrderGroupTourist> getOrderGroupTouristPageList(OrderGroupTourist guest, PageBounds pageBounds);

	//void batInsertSelective(List<OrderGroupTourist> guideTuanGuests);
}