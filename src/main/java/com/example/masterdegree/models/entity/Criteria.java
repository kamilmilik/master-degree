package com.example.masterdegree.models.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode()
@ToString()
public class Criteria {
    @Getter(AccessLevel.NONE)
    private List<String> operatorsId;
    private RangePrice price;
    @Getter(AccessLevel.NONE)
    private List<Channel> channels;
    private String term;

    public List<String> getOperatorsId() {
        if(this.operatorsId == null){
            return new ArrayList<>();
        }
        return Collections.unmodifiableList(operatorsId);
    }

    public List<Channel> getChannels() {
        if(this.channels == null){
            return new ArrayList<>();
        }
        return Collections.unmodifiableList(channels);
    }

    public static Criteria newCriteria(List<String> operatorsId, RangePrice rangePrice, List<Channel> channels, String term){
        return new Criteria(operatorsId, rangePrice, channels, term);
    }
}