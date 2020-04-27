package com.example.masterdegree.models.mappers;

import com.example.masterdegree.models.model.Price;
import com.example.masterdegree.models.dto.CriteriaRequestDto;
import com.example.masterdegree.models.model.Channel;
import com.example.masterdegree.models.model.Criteria;
import com.example.masterdegree.models.model.Term;
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

        return Criteria.newCriteria(criteriaDto.getOperatorsId(), new Price(criteriaDto.getPrice()), channels, new Term(criteriaDto.getTerm()));
    }
}


