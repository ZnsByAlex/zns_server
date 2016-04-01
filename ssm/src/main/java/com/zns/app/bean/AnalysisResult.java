package com.zns.app.bean;

public class AnalysisResult {
    private Integer id;

    private String userno;

    private Integer questioneid;

    private Integer costSaving;

    private String customerBasis;

    private String customerSequenceUser;

    private String effectiveUser;

    private String examinationUserAbc;

    private String optimallineUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserno() {
        return userno;
    }

    public void setUserno(String userno) {
        this.userno = userno;
    }

    public Integer getQuestioneid() {
        return questioneid;
    }

    public void setQuestioneid(Integer questioneid) {
        this.questioneid = questioneid;
    }

    public Integer getCostSaving() {
        return costSaving;
    }

    public void setCostSaving(Integer costSaving) {
        this.costSaving = costSaving;
    }

    public String getCustomerBasis() {
        return customerBasis;
    }

    public void setCustomerBasis(String customerBasis) {
        this.customerBasis = customerBasis;
    }

    public String getCustomerSequenceUser() {
        return customerSequenceUser;
    }

    public void setCustomerSequenceUser(String customerSequenceUser) {
        this.customerSequenceUser = customerSequenceUser;
    }

    public String getEffectiveUser() {
        return effectiveUser;
    }

    public void setEffectiveUser(String effectiveUser) {
        this.effectiveUser = effectiveUser;
    }

    public String getExaminationUserAbc() {
        return examinationUserAbc;
    }

    public void setExaminationUserAbc(String examinationUserAbc) {
        this.examinationUserAbc = examinationUserAbc;
    }

    public String getOptimallineUser() {
        return optimallineUser;
    }

    public void setOptimallineUser(String optimallineUser) {
        this.optimallineUser = optimallineUser;
    }
}