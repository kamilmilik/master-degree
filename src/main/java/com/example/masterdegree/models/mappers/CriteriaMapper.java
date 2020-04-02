package com.example.masterdegree.models.mappers;

import com.example.masterdegree.core.price.RangePrice;
import com.example.masterdegree.models.dto.ChannelRequestDto;
import com.example.masterdegree.models.dto.CriteriaRequestDto;
import com.example.masterdegree.models.entity.Channel;
import com.example.masterdegree.models.entity.Criteria;
import com.example.masterdegree.models.entity.Operator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CriteriaMapper {

    public Criteria convertToEntity(CriteriaRequestDto criteriaDto) {
        List<Channel> channels = criteriaDto.getChannels().stream().map(channelDto ->
                Channel.builder()
                        .name(channelDto.getName())
                        .desc(channelDto.getDesc())
                        .imgSrc(channelDto.getImgSrc()).build()
        ).collect(Collectors.toList());
        return Criteria.builder()
                .operatorsId(criteriaDto.getOperatorsId())
                .price(new RangePrice(criteriaDto.getPrice()))
                .channels(channels)
                .term(criteriaDto.getTerm())
                .build();
    }
}


