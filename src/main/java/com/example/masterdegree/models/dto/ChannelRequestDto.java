package com.example.masterdegree.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelRequestDto {

    @NonNull
    private String name;
    private String desc;
    private String imgSrc;

}