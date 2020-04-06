package com.example.masterdegree.models.mappers;

import com.example.masterdegree.models.model.RangePrice;
import com.example.masterdegree.models.dto.CriteriaRequestDto;
import com.example.masterdegree.models.model.Channel;
import com.example.masterdegree.models.model.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CriteriaMapper {

    public Criteria convertToEntity(CriteriaRequestDto criteriaDto) {
        List<Channel> channels = criteriaDto.getChannels().stream().map(channelDto ->
                Channel.create(channelDto.getName(), channelDto.getImgSrc())
        ).collect(Collectors.toList());

        return Criteria.newCriteria(criteriaDto.getOperatorsId(), new RangePrice(criteriaDto.getPrice()), channels, criteriaDto.getTerm());
    }
}


