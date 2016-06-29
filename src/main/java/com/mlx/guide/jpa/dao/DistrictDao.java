package com.mlx.guide.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mlx.guide.jpa.entities.District;

public interface DistrictDao extends JpaRepository<District, Integer>, JpaSpecificationExecutor<District> {

}
