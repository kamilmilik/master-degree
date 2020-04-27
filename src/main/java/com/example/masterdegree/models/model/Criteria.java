package com.example.masterdegree.models.model;

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
    private Price price;
    @Getter(AccessLevel.NONE)
    private List<Channel> channels;
    private Term term;

    public List<String> getOperatorsId() {
        if (this.operatorsId == null) {
            return new ArrayList<>();
        }
        return Collections.unmodifiableList(operatorsId);
    }

    public List<Channel> getChannels() {
        if (this.channels == null) {
            return new ArrayList<>();
        }
        return Collections.unmodifiableList(channels);
    }

    public boolean hasAnyChannelsCriteria() {
        return channels.size() > 0;
    }

    public static Criteria newCriteria(List<String> operatorsId, Price price, List<Channel> channels, Term term) {
        return new Criteria(operatorsId, price, channels, term);
    }
}