package com.example.masterdegree.core.operator;

import com.example.masterdegree.models.dto.OperatorRequestDto;
import com.example.masterdegree.models.dto.ResultTvPackageResponseDto;
import com.example.masterdegree.models.dto.ResultTvPackagesResponseDto;
import com.example.masterdegree.models.entity.MainTvPackage;
import com.example.masterdegree.models.entity.Operator;

import java.util.List;
import java.util.Set;

public interface OperatorsService {

    List<OperatorRequestDto> getOperatorsFromDbDto();

    List<Operator> getAllOperatorsFromDb();

}
