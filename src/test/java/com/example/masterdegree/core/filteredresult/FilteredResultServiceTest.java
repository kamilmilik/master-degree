package com.example.masterdegree.core.filteredresult;

import com.example.masterdegree.models.dto.CriteriaRequestDto;
import com.example.masterdegree.models.model.Channel;
import com.example.masterdegree.models.model.MainTvPackage;
import com.example.masterdegree.models.model.Operator;
import com.example.masterdegree.models.model.TvPackage;
import com.example.masterdegree.models.model.filter.FilteredTvPackage;
import com.example.masterdegree.models.model.filter.ResultTvPackage;
import com.example.masterdegree.models.model.filter.ResultTvPackages;
import com.example.masterdegree.repositories.OperatorsRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static com.example.masterdegree.Constants.*;
import static com.example.masterdegree.DataCreationUtils.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FilteredResultServiceTest {

    private static final String INVALID_ID = "12243";
    private static final double MAX_PRICE = 400d;

    @Mock
    private OperatorsRepository operatorsRepository;
    @InjectMocks
    private FilteredResultService filteredResultService;

    private static Stream<Arguments> filteredResultWithoutFilters() {
        List<Channel> channels = asList(Channel.channel(TVN), Channel.channel(POLSAT));
        List<TvPackage> extraTvPackages = asList(createTvPackage(ELEVEN_SPORTS), createTvPackage(HBO));
        Operator operatorCanal = createOperator(CANAL_PLUS_ID, CANAL_PLUS, Collections.singletonList(
                createMainTvPackage(COMFORT, channels, extraTvPackages)
        ));
        Operator operatorPolsat = createOperator(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, Collections.singletonList(
                createMainTvPackage(FAMILY_CYFROWY_POLSAT, channels, new LinkedList<>())
        ));

        List<Operator> operators = asList(operatorCanal, operatorPolsat);

        List<ResultTvPackage> expected = new ArrayList<>(asList(
                createResultTvPackage(CANAL_PLUS_ID, CANAL_PLUS, new FilteredTvPackage(
                                createTvPackage(COMFORT, channels),
                                new LinkedList<>(),
                                extraTvPackages
                        )
                ),
                createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                createTvPackage(FAMILY_CYFROWY_POLSAT, channels),
                                new LinkedList<>(),
                                new LinkedList<>()
                        )
                )
        ));

        return Stream.of(Arguments.of(operators, expected));
    }

    private static Stream<Arguments> filteredResult() {
        List<Channel> channels = asList(Channel.channel(TVN), Channel.channel(POLSAT));
        List<Channel> channelsExtra = asList(Channel.channel(TVN), Channel.channel(POLSAT), Channel.channel(HBO_HD_CHANNEL));
        List<Channel> channelsSuperPremium = asList(Channel.channel(TVN), Channel.channel(POLSAT), Channel.channel(HBO_HD_CHANNEL), Channel.channel(BRAZZERS));
        List<TvPackage> extraTvPackages = asList(createTvPackage(ELEVEN_SPORTS), createTvPackage(HBO));
        List<TvPackage> extraTvPackagesWithHbo = asList(createTvPackage(ELEVEN_SPORTS, Collections.singletonList(Channel.channel(ELEVEN_SPORTS_1))), createTvPackage(HBO, Collections.singletonList(Channel.channel(HBO_HD_CHANNEL))));
        MainTvPackage tooLowPriceCriteriaPackage = createMainTvPackage(COMFORT, 20d, TERM_24, channels, extraTvPackages);
        MainTvPackage wrongTermCriteriaPackage = createMainTvPackage(EXTRA, 30d, TERM_12, channelsExtra, extraTvPackages);
        MainTvPackage meetCriteriaAllChannelsInMainPackage = createMainTvPackage(EXTRA, 40d, TERM_24, channelsExtra, extraTvPackages);
        MainTvPackage meetCriteriaChannelsInMainAndExtraPackages = createMainTvPackage(EXTRA, 40d, TERM_24, channels, extraTvPackagesWithHbo);
        MainTvPackage tooHighCriteriaPackage = createMainTvPackage(CANAL_PLUS_SUPERPREMIUM, 60d, TERM_24, channelsSuperPremium, extraTvPackages);
        Operator operatorCanal = createOperator(CANAL_PLUS_ID, CANAL_PLUS, asList(
                tooLowPriceCriteriaPackage,
                wrongTermCriteriaPackage,
                meetCriteriaAllChannelsInMainPackage,
                meetCriteriaChannelsInMainAndExtraPackages,
                tooHighCriteriaPackage
        ));
        Operator operatorPolsat = createOperator(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, Collections.singletonList(
                createMainTvPackage(FAMILY_CYFROWY_POLSAT, 30d, TERM_24, channels, extraTvPackages)
        ));

        List<Operator> operators = asList(operatorCanal, operatorPolsat);

        Operator operatorCanal_2 = createOperator(CANAL_PLUS_ID, CANAL_PLUS, Collections.singletonList(
                createMainTvPackage(COMFORT, 30d, TERM_24, channels, extraTvPackages)
        ));
        Operator operatorPolsat_2 = createOperator(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, Collections.singletonList(
                createMainTvPackage(FAMILY_CYFROWY_POLSAT, 30d, TERM_24, channels, extraTvPackages)
        ));
        List<Operator> operators_2 = asList(operatorCanal_2, operatorPolsat_2);

        CriteriaRequestDto criteria = new CriteriaRequestDto(Collections.singletonList(CANAL_PLUS_ID), 40d, asList(TVN, POLSAT, HBO_HD_CHANNEL), TERM_24);
        CriteriaRequestDto criteriaNotMeetPrice = new CriteriaRequestDto(Collections.singletonList(CANAL_PLUS_ID), 5d, asList(TVN, POLSAT, HBO_HD_CHANNEL), TERM_24);
        CriteriaRequestDto criteriaNotMeetOperators = new CriteriaRequestDto(Collections.singletonList(INVALID_ID), MAX_PRICE, asList(TVN, POLSAT, HBO_HD_CHANNEL), TERM_24);
        CriteriaRequestDto criteriaNotMeetTerm = new CriteriaRequestDto(Collections.singletonList(INVALID_ID), MAX_PRICE, asList(TVN, POLSAT, HBO_HD_CHANNEL), TERM_6);
        CriteriaRequestDto criteriaNotMeetChannels = new CriteriaRequestDto(Collections.singletonList(INVALID_ID), MAX_PRICE, asList(POLSAT_SPORT_PREMIUM, FOX_PLAY), TERM_24);

        CriteriaRequestDto criteriaEmptyOperatorsNotMeetChannels = new CriteriaRequestDto(new LinkedList<>(), MAX_PRICE, asList(POLSAT_SPORT_PREMIUM, FOX_PLAY), TERM_24);
        CriteriaRequestDto criteriaEmptyOperatorsMeetChannels = new CriteriaRequestDto(new LinkedList<>(), MAX_PRICE, asList(TVN, POLSAT, HBO_HD_CHANNEL, BRAZZERS), TERM_24);
        CriteriaRequestDto criteriaMeetAllOperatorsMeetChannels = new CriteriaRequestDto(asList(CANAL_PLUS_ID, CYFROWY_POLSAT_ID), MAX_PRICE, asList(TVN, POLSAT, HBO_HD_CHANNEL, BRAZZERS), TERM_24);
        CriteriaRequestDto criteriaEmptyChannelsNotMeetOperators = new CriteriaRequestDto(Collections.singletonList(INVALID_ID), MAX_PRICE, new LinkedList<>(), TERM_24);
        CriteriaRequestDto criteriaEmptyChannelsMeetOperators = new CriteriaRequestDto(Collections.singletonList(CYFROWY_POLSAT_ID), MAX_PRICE, new LinkedList<>(), TERM_24);
        CriteriaRequestDto criteriaEmptyChannelsEmptyOperators = new CriteriaRequestDto(new LinkedList<>(), MAX_PRICE, new LinkedList<>(), TERM_24);

        ResultTvPackages expected = new ResultTvPackages(new ArrayList<>(asList(
                createResultTvPackage(CANAL_PLUS_ID, CANAL_PLUS, new FilteredTvPackage(
                                createTvPackage(EXTRA, 40d, TERM_24, channelsExtra),
                                new LinkedList<>(),
                                extraTvPackages
                        )
                ),
                createResultTvPackage(CANAL_PLUS_ID, CANAL_PLUS, new FilteredTvPackage(
                                createTvPackage(EXTRA, 40d, TERM_24, channels),
                                Collections.singletonList(createTvPackage(HBO, Collections.singletonList(Channel.channel(HBO_HD_CHANNEL)))),
                                Collections.singletonList(createTvPackage(ELEVEN_SPORTS, Collections.singletonList(Channel.channel(ELEVEN_SPORTS_1))))
                        )
                )
        )));
        ResultTvPackages expectedEmpty = new ResultTvPackages(new ArrayList<>());
        ResultTvPackages expectedMeetChannels = new ResultTvPackages(new ArrayList<>(Collections.singletonList(
                createResultTvPackage(CANAL_PLUS_ID, CANAL_PLUS, new FilteredTvPackage(
                                createTvPackage(CANAL_PLUS_SUPERPREMIUM, 60d, TERM_24, channelsSuperPremium),
                                new LinkedList<>(),
                                extraTvPackages
                        )
                )
        )));
        ResultTvPackages expectedMeetOperator = new ResultTvPackages(new ArrayList<>(Collections.singletonList(
                createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                createTvPackage(FAMILY_CYFROWY_POLSAT, 30d, TERM_24, channels),
                                new LinkedList<>(),
                                extraTvPackages
                        )
                )
        )));
        ResultTvPackages expectedAllPackages = new ResultTvPackages(new ArrayList<>(asList(
                createResultTvPackage(CANAL_PLUS_ID, CANAL_PLUS, new FilteredTvPackage(
                                createTvPackage(COMFORT, 30d, TERM_24, channels),
                                new LinkedList<>(),
                                extraTvPackages
                        )
                ),
                createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                createTvPackage(FAMILY_CYFROWY_POLSAT, 30d, TERM_24, channels),
                                new LinkedList<>(),
                                extraTvPackages
                        )
                )
        )));
        return Stream.of(
                Arguments.of(operators, criteria, expected),
                Arguments.of(operators, criteriaNotMeetPrice, expectedEmpty),
                Arguments.of(operators, criteriaNotMeetOperators, expectedEmpty),
                Arguments.of(operators, criteriaNotMeetTerm, expectedEmpty),
                Arguments.of(operators, criteriaNotMeetOperators, expectedEmpty),
                Arguments.of(operators, criteriaNotMeetChannels, expectedEmpty),
                Arguments.of(operators, criteriaEmptyOperatorsNotMeetChannels, expectedEmpty),
                Arguments.of(operators, criteriaEmptyOperatorsMeetChannels, expectedMeetChannels),
                Arguments.of(operators, criteriaMeetAllOperatorsMeetChannels, expectedMeetChannels),
                Arguments.of(operators, criteriaEmptyChannelsNotMeetOperators, expectedEmpty),
                Arguments.of(operators, criteriaEmptyChannelsMeetOperators, expectedMeetOperator),
                Arguments.of(operators_2, criteriaEmptyChannelsEmptyOperators, expectedAllPackages)
        );
    }

    @ParameterizedTest
    @MethodSource("filteredResult")
    void shouldGetFilteredResult(List<Operator> operators, CriteriaRequestDto criteriaRequestDto, ResultTvPackages expected) {
        when(operatorsRepository.findAll()).thenReturn(operators);

        ResultTvPackages actual = filteredResultService.getFilteredResult(criteriaRequestDto);

        assertThat(actual.getResultTvPackages()).isEqualTo(expected.getResultTvPackages());
    }

    @ParameterizedTest
    @MethodSource("filteredResultWithoutFilters")
    void shouldCreateResultWithoutFilters(List<Operator> operators, List<ResultTvPackage> expected) {
        when(operatorsRepository.findAll()).thenReturn(operators);

        List<ResultTvPackage> actual = filteredResultService.createResultWithoutFilters();

        assertThat(actual).isEqualTo(expected);
    }
}