package com.mlx.guide.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="S_Province")
public class Province {
	
	private Integer  provinceId;
	private String provinceName;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ProvinceID")
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	@Column(name="ProvinceName")
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

}
