package com.example.masterdegree.models.dto;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class OperatorDto {
    private String id;
    private String name;
    private String imgSrc;
}
