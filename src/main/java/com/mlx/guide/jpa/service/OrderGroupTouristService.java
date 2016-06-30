package com.mlx.guide.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mlx.guide.jpa.dao.OrderGroupTouristMapper;
import com.mlx.guide.jpa.entities.OrderGroupTourist;

@Service
@Transactional
public class OrderGroupTouristService {
	@Autowired
	private OrderGroupTouristMapper orderGroupTouristMapper;

	public OrderGroupTourist updateByPrimaryKeySelective(OrderGroupTourist gtg) {

		return orderGroupTouristMapper.saveAndFlush(gtg);
	}

	public Page<OrderGroupTourist> getOrderGroupTouristPageList(OrderGroupTourist guest, Pageable pageable) {
		return orderGroupTouristMapper.findAll(Example.of(guest), pageable);
	}

	public void batInsertSelective(List<OrderGroupTourist> guideTuanGuests) {
	
		orderGroupTouristMapper.save(guideTuanGuests);
		
	}

	public List<OrderGroupTourist> findAll(OrderGroupTourist guest) {
		// TODO Auto-generated method stub
		return orderGroupTouristMapper.findAll(Example.of(guest));
	}

}
