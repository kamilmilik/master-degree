package com.example.masterdegree.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document
public class Operator {

    @Id
    private ObjectId id;

    private String name;

    private String imgSrc;

    private List<TvPackage> mainTvPackages;

    private List<TvPackage> extraTvPackages;

    public Operator(ObjectId id, String name, String imgSrc, List<TvPackage> mainTvPackages, List<TvPackage> extraTvPackages) {
        this.id = id;
        this.name = name;
        this.imgSrc = imgSrc;
        this.mainTvPackages = mainTvPackages;
        this.extraTvPackages = extraTvPackages;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public List<TvPackage> getMainTvPackages() {
        return mainTvPackages;
    }

    public void setMainTvPackages(List<TvPackage> mainTvPackages) {
        this.mainTvPackages = mainTvPackages;
    }

    public List<TvPackage> getExtraTvPackages() {
        return extraTvPackages;
    }

    public void setExtraTvPackages(List<TvPackage> extraTvPackages) {
        this.extraTvPackages = extraTvPackages;
    }

    @Override
    public String toString() {
        return "Operator{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imgSrc='" + imgSrc + '\'' +
                ", mainTvPackages=" + mainTvPackages +
                ", extraTvPackages=" + extraTvPackages +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Operator)) return false;
        Operator operator = (Operator) o;
        return Objects.equals(getId(), operator.getId()) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
