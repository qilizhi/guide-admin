package com.mlx.guide.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mlx.guide.jpa.entities.City;

public interface CityDao extends JpaRepository<City, Integer>,JpaSpecificationExecutor<City> {

	List<City> findByProvinceId(Integer provinceId);

	
}
