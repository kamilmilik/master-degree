package com.example.masterdegree.models.entity;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TvPackage {
    @NonNull
    private String name;
    @NonNull
    private Double price;
    @NonNull
    private String type;
    private String imgSrc;
    @NonNull
    private String link;
    @NonNull
    private String term;
    @NonNull
    private String status;
    @NonNull
    private List<Channel> channels;
}
