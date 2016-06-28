package com.mlx.guide.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.dao.OrderGroupTouristMapper;
import com.mlx.guide.entity.OrderGroupTourist;

@Service
@Transactional
public class OrderGroupTouristService {
	@Autowired
	private OrderGroupTouristMapper orderGroupTouristMapper;

	public int deleteByPrimaryKey(Long id) {

		return orderGroupTouristMapper.deleteByPrimaryKey(id);
	}

	public int insert(OrderGroupTourist record) {

		return orderGroupTouristMapper.insert(record);
	}

	public int insertSelective(OrderGroupTourist record) {

		return orderGroupTouristMapper.insertSelective(record);
	}

	public OrderGroupTourist selectByPrimaryKey(Long id) {

		return orderGroupTouristMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(OrderGroupTourist record) {

		return orderGroupTouristMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(OrderGroupTourist record) {

		return orderGroupTouristMapper.updateByPrimaryKey(record);
	}

	public PageList<OrderGroupTourist> getOrderGroupTouristPageList(OrderGroupTourist record) {

		return orderGroupTouristMapper.getGuideTuanGuestPageList(record);

	}

	public PageList<OrderGroupTourist> getOrderGroupTouristPageList(OrderGroupTourist record, PageBounds pageBounds) {
		return orderGroupTouristMapper.getOrderGroupTouristPageList(record, pageBounds);

	}

	public void batInsertSelective(List<OrderGroupTourist> guideTuanGuests) {
		orderGroupTouristMapper.batInsertSelective(guideTuanGuests);

	}

}
