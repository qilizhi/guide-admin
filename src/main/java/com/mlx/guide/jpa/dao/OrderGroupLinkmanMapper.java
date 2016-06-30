package com.mlx.guide.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mlx.guide.jpa.entities.OrderGroupLinkman;

public interface OrderGroupLinkmanMapper extends JpaRepository<OrderGroupLinkman, Long>,JpaSpecificationExecutor<OrderGroupLinkman>{
    int deleteById(Long id);

    //int insert(OrderGroupLinkman record);

    //int insertSelective(OrderGroupLinkman record);

    OrderGroupLinkman findOneById(Long id);

   // int updateByPrimaryKeySelective(OrderGroupLinkman record);

   // int updateByPrimaryKey(OrderGroupLinkman record);
}