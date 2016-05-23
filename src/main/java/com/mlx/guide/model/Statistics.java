package com.mlx.guide.model;

public class Statistics {
	

	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	private String category;//日期
	private Long value;//用户增长值
	private String color;//颜色

}
