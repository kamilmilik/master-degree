package com.example.masterdegree.models;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.Objects;

public class ResultTvPackage {

    private ObjectId operatorId;

    private String operatorName;

    private String operatorImg;

    private TvPackage mainTPackage;

    private List<TvPackage> extraTvPackages;

    public ResultTvPackage(ObjectId operatorId, String operatorName, String operatorImg, TvPackage mainTPackage, List<TvPackage> extraTvPackages) {
        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.operatorImg = operatorImg;
        this.mainTPackage = mainTPackage;
        this.extraTvPackages = extraTvPackages;
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

    public TvPackage getMainTPackage() {
        return mainTPackage;
    }

    public void setMainTPackage(TvPackage mainTPackage) {
        this.mainTPackage = mainTPackage;
    }

    public List<TvPackage> getExtraTvPackages() {
        return extraTvPackages;
    }

    public void setExtraTvPackages(List<TvPackage> extraTvPackages) {
        this.extraTvPackages = extraTvPackages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResultTvPackage)) return false;
        ResultTvPackage that = (ResultTvPackage) o;
        return Objects.equals(getOperatorId(), that.getOperatorId()) &&
                Objects.equals(getOperatorName(), that.getOperatorName()) &&
                Objects.equals(getOperatorImg(), that.getOperatorImg()) &&
                Objects.equals(getMainTPackage(), that.getMainTPackage()) &&
                Objects.equals(getExtraTvPackages(), that.getExtraTvPackages());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOperatorId(), getOperatorName(), getOperatorImg(), getMainTPackage(), getExtraTvPackages());
    }
}
