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
    List<String> operatorsId;
    Price price;
    @Getter(AccessLevel.NONE)
    List<String> channelsName;
    Term term;

    public List<String> getOperatorsId() {
        if (this.operatorsId == null) {
            return new ArrayList<>();
        }
        return Collections.unmodifiableList(operatorsId);
    }

    public List<String> getChannelsName() {
        if (this.channelsName == null) {
            return new ArrayList<>();
        }
        return Collections.unmodifiableList(channelsName);
    }

    public boolean hasAnyChannelsCriteria() {
        return channelsName.size() > 0;
    }

    public static Criteria newCriteria(List<String> operatorsId, Price price, List<String> channelsName, Term term) {
        return new Criteria(operatorsId, price, channelsName, term);
    }
}