package com.example.masterdegree.models.dto;

import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TvPackageResponseDto {
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
    private List<ChannelDto> channels;
}