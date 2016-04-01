package com.zns.app.bean;

public class Material {
    private Integer materialId;

    private Integer examInfoId;

    private String materialName;

    private String totalNum;

    private String materialSpecification;

    private String materialType;

    private String orderFromNo;

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public Integer getExamInfoId() {
        return examInfoId;
    }

    public void setExamInfoId(Integer examInfoId) {
        this.examInfoId = examInfoId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public String getMaterialSpecification() {
        return materialSpecification;
    }

    public void setMaterialSpecification(String materialSpecification) {
        this.materialSpecification = materialSpecification;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getOrderFromNo() {
        return orderFromNo;
    }

    public void setOrderFromNo(String orderFromNo) {
        this.orderFromNo = orderFromNo;
    }
}