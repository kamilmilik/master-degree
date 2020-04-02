package com.example.masterdegree.models.mappers;


import com.example.masterdegree.models.dto.OperatorRequestDto;
import com.example.masterdegree.models.model.Operator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OperatorMapper {

    private final ModelMapper modelMapper;

    public OperatorRequestDto convertToDto(Operator operator) {
        return modelMapper.map(operator, OperatorRequestDto.class);
    }
}
