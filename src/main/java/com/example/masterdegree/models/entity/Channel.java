package com.example.masterdegree.models.entity;

import lombok.*;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Channel {

    @NonNull
    private String name;
    private String desc;
    private String imgSrc;

}
