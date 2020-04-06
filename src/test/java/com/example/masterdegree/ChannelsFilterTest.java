package com.example.masterdegree;

import com.example.masterdegree.core.filteredresult.FilteredResultService;
import com.example.masterdegree.models.dto.*;
import com.example.masterdegree.models.mappers.CriteriaMapper;
import com.example.masterdegree.models.model.Channel;
import com.example.masterdegree.models.model.MainTvPackage;
import com.example.masterdegree.models.model.Operator;
import com.example.masterdegree.models.model.TvPackage;
import com.example.masterdegree.repositories.OperatorsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChannelsFilterTest extends Constants {
    @Mock
    private OperatorsRepository repositoryOperations;

    @Test
    public void shouldReturnNothing_whenChannelNotExistInAnyTvPackages() {
        Operator operator = create(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, asList(
                create(SMALL_FAMILY_CYFROWY_POLSAT,
                        asList(Channel.create(ELEVEN_SPORTS_1), Channel.create(TVN)),
                        asList(createTvPackage(MULTI_MAN_PACK, asList(Channel.create(DORCEL), Channel.create(BRAZZERS))), createTvPackage(FOX_PLAY, Collections.singletonList(Channel.create(FOX_PLAY))))
                ),
                create(FAMILY_CYFROWY_POLSAT, asList(Channel.create(POLSAT), Channel.create(TVN)),
                        asList(createTvPackage(HBO, asList(Channel.create(HBO), Channel.create(HBO_2))))
                )
        ));
        when(repositoryOperations.findAll()).thenReturn(Collections.singletonList(operator));
        FilteredResultService filteredResultService = new FilteredResultService(repositoryOperations);
        CriteriaRequestDto criteriaChannelElevenSports1 = criteriaChannels(Collections.singletonList(newChannel(FUN_TV)));

        assertThat(filteredResultService.getFilteredResult(criteriaChannelElevenSports1)).isEqualTo(new ResultTvPackagesResponseDto(new LinkedList<>()));

    }

    @Test
    public void shouldReturnElevenSport_whenExistInExtraTvPackage() {
        Operator operatorCanalPlus = create(CANAL_PLUS_ID, CANAL_PLUS, asList(
                create(CANAL_PLUS_START,
                        asList(Channel.create(FUN_TV), Channel.create(TVN)),
                        asList(createTvPackage(HBO, asList(Channel.create(HBO), Channel.create(HBO_2))), createTvPackage(MULTI_MAN_PACK, asList(Channel.create(DORCEL), Channel.create(BRAZZERS))))),
                create(CANAL_PLUS_SUPERPREMIUM,
                        asList(Channel.create(POLSAT), Channel.create(TVN)),
                        asList(createTvPackage(HBO, asList(Channel.create(HBO), Channel.create(HBO_2))), createTvPackage(POLSAT_SPORT_PREMIUM, asList(Channel.create(VIVD_HD), Channel.create(BRAZZERS))))
                )));
        Operator operatorCyfrowyPolsat = create(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, asList(
                create(SMALL_FAMILY_CYFROWY_POLSAT,
                        asList(Channel.create(FUN_TV), Channel.create(TVN)),
                        asList(createTvPackage(ELEVEN_SPORTS, asList(Channel.create(ELEVEN_SPORTS_1), Channel.create(ELEVEN_SPORTS_2))), createTvPackage(MULTI_MAN_PACK, asList(Channel.create(DORCEL), Channel.create(BRAZZERS))))),
                create(FAMILY_CYFROWY_POLSAT, asList(Channel.create(POLSAT), Channel.create(TVN)),
                        asList(createTvPackage(HBO, asList(Channel.create(HBO), Channel.create(HBO_2))), createTvPackage(ELEVEN_SPORTS, asList(Channel.create(ELEVEN_SPORTS_1), Channel.create(ELEVEN_SPORTS_2))))
                )));
        when(repositoryOperations.findAll()).thenReturn(asList(operatorCanalPlus, operatorCyfrowyPolsat));
        FilteredResultService filteredResultService = new FilteredResultService(repositoryOperations);
        CriteriaRequestDto criteriaChannelElevenSports1 = criteriaChannels(Collections.singletonList(newChannel(ELEVEN_SPORTS_1)));

        ResultTvPackagesResponseDto expectedResultElevenData = new ResultTvPackagesResponseDto(asList(
                create(CYFROWY_POLSAT_ID, CYFROWY_POLSAT,
                        new FilteredTvPackageResponseDto(
                                create(SMALL_FAMILY_CYFROWY_POLSAT, asList(create(FUN_TV), create(TVN))),
                                asList(create(ELEVEN_SPORTS, asList(create(ELEVEN_SPORTS_1), create(ELEVEN_SPORTS_2)))),
                                asList(
                                        create(ELEVEN_SPORTS, asList(create(ELEVEN_SPORTS_1), create(ELEVEN_SPORTS_2))),
                                        create(MULTI_MAN_PACK, asList(create(DORCEL), create(BRAZZERS)))
                                )
                        )
                ),
                create(CYFROWY_POLSAT_ID, CYFROWY_POLSAT,
                        new FilteredTvPackageResponseDto(
                                create(FAMILY_CYFROWY_POLSAT, asList(create(POLSAT), create(TVN))),
                                asList(create(ELEVEN_SPORTS, asList(create(ELEVEN_SPORTS_1), create(ELEVEN_SPORTS_2)))),
                                asList(
                                        create(HBO, asList(create(HBO), create(HBO_2))),
                                        create(ELEVEN_SPORTS, asList(create(ELEVEN_SPORTS_1), create(ELEVEN_SPORTS_2)))
                                )
                        )
                )
        ));

        assertThat(filteredResultService.getFilteredResult(criteriaChannelElevenSports1)).isEqualTo(expectedResultElevenData);
    }

    @Test
    public void shouldReturnElevenSport_whenExistInMainTvPackage() {
        Operator operator = create(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, asList(
                create(SMALL_FAMILY_CYFROWY_POLSAT,
                        asList(Channel.create(ELEVEN_SPORTS_1), Channel.create(TVN)),
                        asList(createTvPackage(MULTI_MAN_PACK, asList(Channel.create(DORCEL), Channel.create(BRAZZERS))), createTvPackage(HBO, asList(Channel.create(HBO), Channel.create(HBO_2))))),
                create(FAMILY_CYFROWY_POLSAT, asList(Channel.create(POLSAT), Channel.create(TVN)),
                        asList(createTvPackage(HBO, asList(Channel.create(HBO), Channel.create(HBO_2))))
                )
        ));

        when(repositoryOperations.findAll()).thenReturn(asList(operator));
        FilteredResultService filteredResultService = new FilteredResultService(repositoryOperations);
        CriteriaRequestDto criteriaChannelElevenSports1 = criteriaChannels(Collections.singletonList(newChannel(ELEVEN_SPORTS_1)));

        ResultTvPackagesResponseDto expectedResult = new ResultTvPackagesResponseDto(asList(
                create(CYFROWY_POLSAT_ID, CYFROWY_POLSAT,
                        new FilteredTvPackageResponseDto(
                                create(SMALL_FAMILY_CYFROWY_POLSAT, asList(create(ELEVEN_SPORTS_1), create(TVN))),
                                new LinkedList<>(),
                                asList(
                                        create(MULTI_MAN_PACK, asList(create(DORCEL), create(BRAZZERS))),
                                        create(HBO, asList(create(HBO), create(HBO_2)))
                                )
                        )
                )
        ));
        assertThat(filteredResultService.getFilteredResult(criteriaChannelElevenSports1)).isEqualTo(expectedResult);
    }

    @Test
    public void shouldReturnElevenSportAndFoxPlay_whenOneExistInMainTvPackageAndSecondExistInExtraTvPackage() {
        Operator operator = create(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, asList(
                create(SMALL_FAMILY_CYFROWY_POLSAT,
                        asList(Channel.create(ELEVEN_SPORTS_1), Channel.create(TVN)),
                        asList(createTvPackage(MULTI_MAN_PACK, asList(Channel.create(DORCEL), Channel.create(BRAZZERS))), createTvPackage(FOX_PLAY, Collections.singletonList(Channel.create(FOX_PLAY))))
                ),
                create(FAMILY_CYFROWY_POLSAT, asList(Channel.create(POLSAT), Channel.create(TVN)),
                        asList(createTvPackage(HBO, asList(Channel.create(HBO), Channel.create(HBO_2))))
                )
        ));

        when(repositoryOperations.findAll()).thenReturn(Collections.singletonList(operator));
        FilteredResultService filteredResultService = new FilteredResultService(repositoryOperations);
        CriteriaRequestDto criteriaChannelElevenSports1 = criteriaChannels(asList(newChannel(ELEVEN_SPORTS_1), newChannel(FOX_PLAY)));

        ResultTvPackagesResponseDto expectedResult = new ResultTvPackagesResponseDto(Collections.singletonList(
                create(CYFROWY_POLSAT_ID, CYFROWY_POLSAT,
                        new FilteredTvPackageResponseDto(
                                create(SMALL_FAMILY_CYFROWY_POLSAT, asList(create(ELEVEN_SPORTS_1), create(TVN))),
                                Collections.singletonList(create(FOX_PLAY, Collections.singletonList(create(FOX_PLAY)))),
                                asList(
                                        create(MULTI_MAN_PACK, asList(create(DORCEL), create(BRAZZERS))),
                                        create(FOX_PLAY, Collections.singletonList(create(FOX_PLAY)))
                                )
                        )
                )
        ));
        assertThat(filteredResultService.getFilteredResult(criteriaChannelElevenSports1)).isEqualTo(expectedResult);
    }

    @Test
    public void shouldReturnElevenSport_whenExistInTwoExtraTvPackages() {
        Operator operator = create(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, asList(
                create(SMALL_FAMILY_CYFROWY_POLSAT,
                        asList(Channel.create(POLSAT), Channel.create(TVN)),
                        asList(createTvPackage(MULTI_MAN_PACK, asList(Channel.create(ELEVEN_SPORTS_1), Channel.create(BRAZZERS))), createTvPackage(ELEVEN_SPORTS, Collections.singletonList(Channel.create(ELEVEN_SPORTS_1))))
                ),
                create(FAMILY_CYFROWY_POLSAT, asList(Channel.create(POLSAT), Channel.create(TVN)),
                        asList(createTvPackage(HBO, asList(Channel.create(HBO), Channel.create(HBO_2))))
                )
        ));

        when(repositoryOperations.findAll()).thenReturn(Collections.singletonList(operator));
        FilteredResultService filteredResultService = new FilteredResultService(repositoryOperations);
        CriteriaRequestDto criteriaChannelElevenSports1 = criteriaChannels(asList(newChannel(ELEVEN_SPORTS_1)));

        ResultTvPackagesResponseDto expectedResult = new ResultTvPackagesResponseDto(Collections.singletonList(
                create(CYFROWY_POLSAT_ID, CYFROWY_POLSAT,
                        new FilteredTvPackageResponseDto(
                                create(SMALL_FAMILY_CYFROWY_POLSAT, asList(create(POLSAT), create(TVN))),
                                asList(create(MULTI_MAN_PACK, asList(create(ELEVEN_SPORTS_1), create(BRAZZERS))), create(ELEVEN_SPORTS, Collections.singletonList(create(ELEVEN_SPORTS_1)))),
                                asList(
                                        create(MULTI_MAN_PACK, asList(create(ELEVEN_SPORTS_1), create(BRAZZERS))),
                                        create(ELEVEN_SPORTS, Collections.singletonList(create(ELEVEN_SPORTS_1)))
                                )
                        )
                )
        ));
        assertThat(filteredResultService.getFilteredResult(criteriaChannelElevenSports1)).isEqualTo(expectedResult);
    }


    public static Operator create(String id, String name, List<MainTvPackage> mainTvPackages) {
        return new Operator(id, name, "", mainTvPackages);
    }

    public static MainTvPackage create(String name, List<Channel> channels, List<TvPackage> extraTvPackages) {
        return new MainTvPackage(name, "", 0d, "", "", "", "", "", channels, extraTvPackages);
    }

    public static TvPackage createTvPackage(String name, List<Channel> channels) {
        return new TvPackage(name, "", 0d, "", "", "", "", "", channels);
    }

    public static ResultTvPackageResponseDto create(String operatorId, String operatorName, FilteredTvPackageResponseDto filteredTvPackageResponseDto) {
        return new ResultTvPackageResponseDto(operatorId, operatorName, "", filteredTvPackageResponseDto);
    }

    public static TvPackageResponseDto create(String name, List<ChannelDto> channels) {
        return new TvPackageResponseDto(name, "", 0d, "", "", "", "", channels);
    }

    public static ChannelDto create(String name) {
        return new ChannelDto(name, null);
    }

    private CriteriaRequestDto criteriaChannels(List<ChannelDto> channels) {
        return new CriteriaRequestDto(null, new double[]{0, 400}, channels, "24");
    }

    public ChannelDto newChannel(String name) {
        return new ChannelDto(name, null);
    }
}
