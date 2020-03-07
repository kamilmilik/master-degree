package com.example.masterdegree.models;

import lombok.EqualsAndHashCode;
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

    private List<TvPackage> tvPackages;

    public Operator(ObjectId id, String name, String imgSrc, List<TvPackage> tvPackages) {
        this.id = id;
        this.name = name;
        this.imgSrc = imgSrc;
        this.tvPackages = tvPackages;
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

    public List<TvPackage> getTvPackages() {
        return tvPackages;
    }

    public void setTvPackages(List<TvPackage> tvPackages) {
        this.tvPackages = tvPackages;
    }

    @Override
    public String toString() {
        return "Operator{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imgSrc='" + imgSrc + '\'' +
                ", tvPackages=" + tvPackages +
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
