package com.example.masterdegree.core.operator;

import com.example.masterdegree.models.dto.OperatorRequestDto;
import com.example.masterdegree.models.dto.ResultTvPackagesResponseDto;
import com.example.masterdegree.models.entity.MainTvPackage;
import com.example.masterdegree.models.entity.Operator;
import com.example.masterdegree.models.dto.ResultTvPackageResponseDto;
import com.example.masterdegree.models.mappers.OperatorMapper;
import com.example.masterdegree.repositories.OperatorsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OperatorsServiceImpl implements OperatorsService {

    private final OperatorsRepository operatorsRepository;
    private final OperatorMapper operatorMapper;

    @Override
    public List<OperatorRequestDto> getOperatorsFromDbDto() {
        return getAllOperatorsFromDb().stream().map(operatorMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<Operator> getAllOperatorsFromDb() {
        return operatorsRepository.findAll();
    }

}
