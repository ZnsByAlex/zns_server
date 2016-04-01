package com.zns.app.bean;

public class Order {
    private Integer orderId;

    private Integer examInfoId;

    private Integer orderNo;

    private String orderEffective;

    private String orderType;

    private String customerName;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getExamInfoId() {
        return examInfoId;
    }

    public void setExamInfoId(Integer examInfoId) {
        this.examInfoId = examInfoId;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderEffective() {
        return orderEffective;
    }

    public void setOrderEffective(String orderEffective) {
        this.orderEffective = orderEffective;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}