package com.example.masterdegree.models.entity;

import lombok.*;

import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TvPackage {
    @NonNull
    private String name;
    private String description;
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
    @Getter(AccessLevel.NONE)
    private List<Channel> channels;

    public List<Channel> getChannels() {
        return Collections.unmodifiableList(channels);
    }

}
