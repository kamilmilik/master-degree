package com.example.masterdegree;

import com.example.masterdegree.core.filteredresult.FilteredResultServiceImpl;
import com.example.masterdegree.core.strategyfilters.*;
import com.example.masterdegree.models.dto.*;
import com.example.masterdegree.models.mappers.CriteriaMapper;
import com.example.masterdegree.models.mappers.ResultTvPackagesMapper;
import com.example.masterdegree.models.model.*;
import com.example.masterdegree.models.model.filter.FilteredTvPackage;
import com.example.masterdegree.models.model.filter.ResultTvPackage;
import com.example.masterdegree.models.model.filter.ResultTvPackages;
import com.example.masterdegree.repositories.OperatorsRepository;
import com.sun.corba.se.spi.activation.RepositoryOperations;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChannelsFilterTest extends Constants{
    @Mock
    private OperatorsRepository repositoryOperations;

    @Mock
    private CriteriaMapper criteriaMapper;

    @InjectMocks
    private FilteredResultServiceImpl filteredResultService;

    @Before
    public void setup() {
        when(repositoryOperations.findAll()).thenReturn(OperatorsDataTestFactory.create());
    }

    @Test
    public void testFilteringInMainTvPackage() {
        criteriaMapper = new CriteriaMapper();
        ResultTvPackagesMapper resultTvPackagesMapper = new ResultTvPackagesMapper(new ModelMapper());
        FilteredResultServiceImpl filteredResultService = new FilteredResultServiceImpl(repositoryOperations, criteriaMapper, resultTvPackagesMapper);
        CriteriaRequestDto criteriaChannelElevenSports1 = criteriaChannels(Collections.singletonList(newChannel(ELEVEN_SPORTS1)));
        CriteriaRequestDto criteriaChannelElevenSports1AndFoxPlay = criteriaChannels(asList(newChannel(ELEVEN_SPORTS1), newChannel(FOX_PLAY)));

        assertThat(filteredResultService.getFilteredResult(criteriaChannelElevenSports1)).isEqualTo(OperatorsDataTestFactory.ExpectedResultDataTestFactory.expectedResultChannelEleven());
    }



    private CriteriaRequestDto criteriaChannels(List<ChannelDto> channels){
        return new CriteriaRequestDto(null,new double[]{0, 400}, channels,"24");
    }


    public ChannelDto newChannel(String name){
        return new ChannelDto(name, null);
    }


}
