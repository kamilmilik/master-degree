package com.example.masterdegree.models.dto;

import com.example.masterdegree.core.price.RangePrice;
import com.example.masterdegree.models.entity.Channel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode()
@ToString()
public class CriteriaDto {
    private List<String> operatorsId;
    private RangePrice price;
    private List<Channel> channels;
    private String term;
}
