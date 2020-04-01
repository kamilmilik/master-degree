package com.example.masterdegree.models.mappers;

import com.example.masterdegree.core.price.RangePrice;
import com.example.masterdegree.models.dto.CriteriaRequestDto;
import com.example.masterdegree.models.entity.Criteria;
import com.example.masterdegree.models.entity.Operator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CriteriaMapper {

    private final ModelMapper modelMapper;

    public CriteriaRequestDto convertToDto(Criteria criteria) {
        return modelMapper.map(criteria, CriteriaRequestDto.class);
    }

    public Criteria convertToEntity(CriteriaRequestDto criteriaRequestDto) {
        this.modelMapper.typeMap(CriteriaRequestDto.class, Criteria.class)
                .addMappings(mapper -> mapper.using(mappingContext -> new RangePrice((double[]) mappingContext.getSource()))
                        .map(CriteriaRequestDto::getPrice, Criteria::setPrice));
        return modelMapper.map(criteriaRequestDto, Criteria.class);
    }
}
