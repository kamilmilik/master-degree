package com.example.masterdegree.models.entity;

import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
public class TvPackage {
    @NonNull
    @Getter
    private String name;
    @Getter
    private String description;
    @NonNull
    @Getter
    private Double price;
    @NonNull
    @Getter
    private String type;
    @Getter
    private String imgSrc;
    @NonNull
    @Getter
    private String link;
    @NonNull
    @Getter
    private String term;
    @NonNull
    @Getter
    private String status;
    @NonNull
    @Getter
    private List<Channel> channels;

}
