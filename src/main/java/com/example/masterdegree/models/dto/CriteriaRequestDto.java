package com.example.masterdegree.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode()
@ToString()
public class CriteriaRequestDto {
    private List<String> operatorsId;
    private double price;
    private List<String> channelsName;
    private String term;
}
