package com.example.masterdegree.core.operator;

import com.example.masterdegree.models.dto.OperatorRequestDto;
import com.example.masterdegree.models.model.Operator;

import java.util.List;

public interface OperatorsService {

    List<OperatorRequestDto> getOperatorsFromDbDto();

    List<Operator> getAllOperatorsFromDb();

}
