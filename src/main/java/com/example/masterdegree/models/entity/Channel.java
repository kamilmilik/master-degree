package com.example.masterdegree.models.entity;

import lombok.*;

@Data
@AllArgsConstructor
public class Channel {

    @NonNull
    private String name;
    @NonNull
    private String desc;
    private String imgSrc;

}
