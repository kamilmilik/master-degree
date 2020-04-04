package com.example.masterdegree.models.dto;

import com.example.masterdegree.models.model.Channel;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"name"})
public class ChannelDto {

    @NonNull
    private String name;
    private String imgSrc;

}