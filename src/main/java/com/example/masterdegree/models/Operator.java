package com.example.masterdegree.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Operator {

    @Id
    private ObjectId id;
    private String name;

    private List<TvPackage> tvPackages;

    public Operator(ObjectId id, String name, List<TvPackage> tvPackages) {
        this.id = id;
        this.name = name;
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

    public List<TvPackage> getTvPackages() {
        return tvPackages;
    }

    public void setTvPackages(List<TvPackage> tvPackages) {
        this.tvPackages = tvPackages;
    }
}
