package com.example.masterdegree.models.entity;

import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MainTvPackage extends TvPackage {

    private List<TvPackage> extraTvPackages;

    public MainTvPackage(String name, Double price, String type, String imgSrc,String link, String term, String status, List<Channel> channels, List<TvPackage> extraTvPackages) {
        super(name, price, type, imgSrc, link, term, status, channels);
        this.extraTvPackages = extraTvPackages;
    }
}
