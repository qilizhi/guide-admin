package com.mlx.guide.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mlx.guide.jpa.dao.OrderGroupLinkmanMapper;
import com.mlx.guide.jpa.entities.OrderGroupLinkman;

@Service
@Transactional
public class OrderGroupLinkmanService {
	@Autowired
	private OrderGroupLinkmanMapper orderGroupLinkmanMapper;

	public OrderGroupLinkman saveAndFlush(OrderGroupLinkman or) {

		return orderGroupLinkmanMapper.saveAndFlush(or);
	}

	public Long countByExample(OrderGroupLinkman orderG) {

		return orderGroupLinkmanMapper.count(Example.of(orderG));
	}

}
