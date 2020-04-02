package com.example.masterdegree.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelDto {

    @NonNull
    private String name;
    private String imgSrc;

}