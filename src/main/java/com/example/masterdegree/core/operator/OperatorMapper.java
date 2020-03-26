package com.example.masterdegree.core.operator;


import com.example.masterdegree.models.dto.OperatorDto;
import com.example.masterdegree.models.entity.Operator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OperatorMapper {

    private final ModelMapper modelMapper;

    public OperatorDto convertToDto(Operator operator) {
        return modelMapper.map(operator, OperatorDto.class);
    }

    public Operator convertToEntity(OperatorDto operatorDto) {
        return modelMapper.map(operatorDto, Operator.class);
    }

}
