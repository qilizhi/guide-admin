package com.mlx.guide.entity;

import java.math.BigDecimal;
import java.util.Date;

public class GuideServicePrice {
    private Long id;

    private String serviceNo;

    private BigDecimal mlxPrice;

    private Integer num;

    private Date lineDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceNo() {
        return serviceNo;
    }

    public void setServiceNo(String serviceNo) {
        this.serviceNo = serviceNo;
    }

    public BigDecimal getMlxPrice() {
        return mlxPrice;
    }

    public void setMlxPrice(BigDecimal mlxPrice) {
        this.mlxPrice = mlxPrice;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getLineDate() {
        return lineDate;
    }

    public void setLineDate(Date lineDate) {
        this.lineDate = lineDate;
    }
}