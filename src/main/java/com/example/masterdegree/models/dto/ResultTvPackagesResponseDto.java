package com.example.masterdegree.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResultTvPackagesResponseDto {

    private List<ResultTvPackageResponseDto> resultTvPackageResponseDtos;

}
