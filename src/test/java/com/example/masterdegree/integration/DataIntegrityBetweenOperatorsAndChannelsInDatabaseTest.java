package com.example.masterdegree.integration;

import com.example.masterdegree.MasterDegreeApplication;
import com.example.masterdegree.core.filteredresult.FilteredResultService;
import com.example.masterdegree.core.strategyfilters.ChannelCriteriaStrategy;
import com.example.masterdegree.models.model.*;
import com.example.masterdegree.models.model.filter.ResultTvPackage;
import com.example.masterdegree.repositories.ChannelsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {MasterDegreeApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DataIntegrityBetweenOperatorsAndChannelsInDatabaseTest {
    static final Logger LOGGER = LoggerFactory.getLogger(DataIntegrityBetweenOperatorsAndChannelsInDatabaseTest.class);
    @Autowired
    private ChannelsRepository channelsRepository;
    @Autowired
    FilteredResultService filteredResultService;

    @Test
    void shouldReturnSomeResults() {
        List<ChannelsGroupByCategory> channelsGroupByCategory = channelsRepository.findAll();
        List<Integer> actualSizes = new ArrayList<>();
        List<ResultTvPackage> input = filteredResultService.createResultWithoutFilters();
        for (ChannelsGroupByCategory categoryWithChannels : channelsGroupByCategory) {
            for (Channel channel : categoryWithChannels.getChannels()) {
                List<ResultTvPackage> newInput = new ArrayList<>(input);
                ChannelCriteriaStrategy channelCriteriaStrategy = new ChannelCriteriaStrategy(create(channel));
                List<ResultTvPackage> actual = channelCriteriaStrategy.getFilteredResult(newInput);
                actualSizes.add(actual.size());
                LOGGER.info("For channel: " + channel.getName() + " result size is:" + actual.size());
            }
        }

        assertThat(actualSizes, everyItem(greaterThanOrEqualTo(1)));
    }

    private static Criteria create(Channel channel) {
        return Criteria.newCriteria(new ArrayList<>(), new Price(400d), Collections.singletonList(channel), new Term("0"));
    }

}
