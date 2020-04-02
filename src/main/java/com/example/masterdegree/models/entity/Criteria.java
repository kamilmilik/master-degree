package com.example.masterdegree.models.entity;

import com.example.masterdegree.core.price.RangePrice;
import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Builder
@Value
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
}