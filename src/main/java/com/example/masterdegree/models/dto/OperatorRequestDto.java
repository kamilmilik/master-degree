package com.example.masterdegree.models.dto;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class OperatorRequestDto {
    private String id;
}
