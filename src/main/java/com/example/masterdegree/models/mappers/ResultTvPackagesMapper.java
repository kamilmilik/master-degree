package com.example.masterdegree.models.mappers;


import com.example.masterdegree.models.dto.ResultTvPackagesResponseDto;
import com.example.masterdegree.models.model.filter.ResultTvPackages;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ResultTvPackagesMapper {

    private final ModelMapper modelMapper;

    public ResultTvPackagesResponseDto convertToDto(ResultTvPackages resultTvPackages) {
        return modelMapper.map(resultTvPackages, ResultTvPackagesResponseDto.class);
    }

}
