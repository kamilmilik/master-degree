package com.example.masterdegree.models;

import org.bson.types.ObjectId;

import java.util.Objects;

public class ResultTvPackage {

    private ObjectId operatorId;

    private String operatorName;

    private String operatorImg;

    private TvPackage tvPackage;

    public ResultTvPackage(ObjectId operatorId, String operatorName, String operatorImg, TvPackage tvPackage) {
        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.operatorImg = operatorImg;
        this.tvPackage = tvPackage;
    }

    public ObjectId getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(ObjectId operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorImg() {
        return operatorImg;
    }

    public void setOperatorImg(String operatorImg) {
        this.operatorImg = operatorImg;
    }

    public TvPackage getTvPackage() {
        return tvPackage;
    }

    public void setTvPackage(TvPackage tvPackage) {
        this.tvPackage = tvPackage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResultTvPackage)) return false;
        ResultTvPackage that = (ResultTvPackage) o;
        return Objects.equals(getOperatorId(), that.getOperatorId()) &&
                Objects.equals(getOperatorName(), that.getOperatorName()) &&
                Objects.equals(getOperatorImg(), that.getOperatorImg()) &&
                Objects.equals(getTvPackage(), that.getTvPackage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOperatorId(), getOperatorName(), getOperatorImg(), getTvPackage());
    }
}
