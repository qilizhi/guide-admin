package com.mlx.guide.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mlx.guide.jpa.entities.Province;

public interface ProvinceDao extends JpaRepository<Province, Integer>,JpaSpecificationExecutor<Province> {

}
