package com.example.masterdegree.models.model;

import lombok.*;

import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MainTvPackage extends TvPackage {

    private List<TvPackage> extraTvPackages;

    public List<TvPackage> getExtraTvPackages() {
        return Collections.unmodifiableList(extraTvPackages);
    }

    public MainTvPackage(@NonNull String name, String description, @NonNull Double price, @NonNull String type, String imgSrc, @NonNull String link, @NonNull String term, @NonNull String status, @NonNull List<Channel> channels, List<TvPackage> extraTvPackages) {
        super(name, description, price, type, imgSrc, link, term, status, channels);
        this.extraTvPackages = extraTvPackages;
    }
}
