package com.example.masterdegree.core.operator;

import com.example.masterdegree.models.dto.OperatorRequestDto;
import com.example.masterdegree.models.mappers.OperatorMapper;
import com.example.masterdegree.models.model.Operator;
import com.example.masterdegree.repositories.OperatorsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OperatorsService {

    private final OperatorsRepository operatorsRepository;
    private final OperatorMapper operatorMapper;

    public List<OperatorRequestDto> getOperatorsFromDbDto() {
        return getAllOperatorsFromDb().stream().map(operatorMapper::convertToDto).collect(Collectors.toList());
    }

    public List<Operator> getAllOperatorsFromDb() {
        return operatorsRepository.findAll();
    }
}
