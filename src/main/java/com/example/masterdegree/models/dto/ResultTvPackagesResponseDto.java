package com.example.masterdegree.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultTvPackagesResponseDto {

    private List<ResultTvPackageResponseDto> resultTvPackages;

}
