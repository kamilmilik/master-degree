package com.example.masterdegree.core.strategyfilters;

import com.example.masterdegree.models.model.Channel;
import com.example.masterdegree.models.model.Criteria;
import com.example.masterdegree.models.model.filter.FilteredTvPackage;
import com.example.masterdegree.models.model.filter.ResultTvPackage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static com.example.masterdegree.Constants.*;
import static com.example.masterdegree.core.strategyfilters.DataCreationUtils.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class ChannelCriteriaStrategyTest {

    @Test
    public void shouldReturnNothing_whenChannelNotExistInAnyTvPackages() {
        List<ResultTvPackage> resultTvPackage = new ArrayList<>(asList(
                createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                createTvPackage(SMALL_FAMILY_CYFROWY_POLSAT,
                                        asList(Channel.create(ELEVEN_SPORTS_1), Channel.create(TVN))
                                ),
                                new LinkedList<>(),
                                asList(createTvPackage(MULTI_MAN_PACK, asList(Channel.create(DORCEL_TV_HD), Channel.create(BRAZZERS))), createTvPackage(FOX_PLAY, Collections.singletonList(Channel.create(FOX_PLAY))))
                        )
                ),
                createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                createTvPackage(FAMILY_CYFROWY_POLSAT, asList(Channel.create(POLSAT), Channel.create(TVN))),
                                new LinkedList<>(),
                                asList(createTvPackage(HBO_HD_CHANNEL, asList(Channel.create(HBO_HD_CHANNEL), Channel.create(HBO_2_HD_CHANNEL))))
                        )
                )
        ));
        Criteria criteria = createCriteria(Collections.singletonList(Channel.create(FUN_TV)));
        ChannelCriteriaStrategy channelCriteriaStrategy = new ChannelCriteriaStrategy(criteria);

        List<ResultTvPackage> actual = channelCriteriaStrategy.getFilteredResult(resultTvPackage);

        List<ResultTvPackage> expected = new LinkedList<>();
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void shouldReturnElevenSport_whenExistInExtraTvPackage() {
        List<ResultTvPackage> resultTvPackage = new ArrayList<>(asList(
                createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                createTvPackage(SMALL_FAMILY_CYFROWY_POLSAT,
                                        asList(Channel.create(FUN_TV), Channel.create(TVN))
                                ),
                                new LinkedList<>(),
                                asList(createTvPackage(ELEVEN_SPORTS, asList(Channel.create(ELEVEN_SPORTS_1), Channel.create(ELEVEN_SPORTS_2))), createTvPackage(MULTI_MAN_PACK, asList(Channel.create(DORCEL_TV_HD), Channel.create(BRAZZERS))))
                        )
                ),
                createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                createTvPackage(FAMILY_CYFROWY_POLSAT, asList(Channel.create(POLSAT), Channel.create(TVN))),
                                new LinkedList<>(),
                                asList(createTvPackage(HBO_HD_CHANNEL, asList(Channel.create(HBO_HD_CHANNEL), Channel.create(HBO_2_HD_CHANNEL))), createTvPackage(ELEVEN_SPORTS, asList(Channel.create(ELEVEN_SPORTS_1), Channel.create(ELEVEN_SPORTS_2))))
                        )
                ),
                createResultTvPackage(CANAL_PLUS_ID, CANAL_PLUS, new FilteredTvPackage(
                                createTvPackage(CANAL_PLUS_START, asList(Channel.create(FUN_TV), Channel.create(TVN))),
                                new LinkedList<>(),
                                asList(createTvPackage(HBO_HD_CHANNEL, asList(Channel.create(HBO_HD_CHANNEL), Channel.create(HBO_2_HD_CHANNEL))), createTvPackage(MULTI_MAN_PACK, asList(Channel.create(DORCEL_TV_HD), Channel.create(BRAZZERS))))
                        )
                ),
                createResultTvPackage(CANAL_PLUS_ID, CANAL_PLUS, new FilteredTvPackage(
                                createTvPackage(CANAL_PLUS_SUPERPREMIUM, asList(Channel.create(POLSAT), Channel.create(TVN))),
                                new LinkedList<>(),
                                asList(createTvPackage(HBO_HD_CHANNEL, asList(Channel.create(HBO_HD_CHANNEL), Channel.create(HBO_2_HD_CHANNEL))), createTvPackage(POLSAT_SPORT_PREMIUM, asList(Channel.create(VIVD_HD), Channel.create(BRAZZERS))))
                        )
                )
        ));
        Criteria criteria = createCriteria(Collections.singletonList(Channel.create(ELEVEN_SPORTS_1)));
        ChannelCriteriaStrategy channelCriteriaStrategy = new ChannelCriteriaStrategy(criteria);

        List<ResultTvPackage> actual = channelCriteriaStrategy.getFilteredResult(resultTvPackage);

        List<ResultTvPackage> expected = asList(
                createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT,
                        new FilteredTvPackage(
                                createTvPackage(SMALL_FAMILY_CYFROWY_POLSAT, asList(Channel.create(FUN_TV), Channel.create(TVN))),
                                asList(createTvPackage(ELEVEN_SPORTS, asList(Channel.create(ELEVEN_SPORTS_1), Channel.create(ELEVEN_SPORTS_2)))),
                                asList(
                                        createTvPackage(ELEVEN_SPORTS, asList(Channel.create(ELEVEN_SPORTS_1), Channel.create(ELEVEN_SPORTS_2))),
                                        createTvPackage(MULTI_MAN_PACK, asList(Channel.create(DORCEL_TV_HD), Channel.create(BRAZZERS)))
                                )
                        )
                ),
                createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT,
                        new FilteredTvPackage(
                                createTvPackage(FAMILY_CYFROWY_POLSAT, asList(Channel.create(POLSAT), Channel.create(TVN))),
                                asList(createTvPackage(ELEVEN_SPORTS, asList(Channel.create(ELEVEN_SPORTS_1), Channel.create(ELEVEN_SPORTS_2)))),
                                asList(
                                        createTvPackage(HBO_HD_CHANNEL, asList(Channel.create(HBO_HD_CHANNEL), Channel.create(HBO_2_HD_CHANNEL))),
                                        createTvPackage(ELEVEN_SPORTS, asList(Channel.create(ELEVEN_SPORTS_1), Channel.create(ELEVEN_SPORTS_2)))
                                )
                        )
                )
        );
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldReturnElevenSport_whenExistInMainTvPackage() {
        List<ResultTvPackage> resultTvPackage = new ArrayList<>(asList(
                createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                createTvPackage(SMALL_FAMILY_CYFROWY_POLSAT,
                                        asList(Channel.create(ELEVEN_SPORTS_1), Channel.create(TVN))
                                ),
                                new LinkedList<>(),
                                asList(createTvPackage(MULTI_MAN_PACK, asList(Channel.create(DORCEL_TV_HD), Channel.create(BRAZZERS))), createTvPackage(HBO_HD_CHANNEL, asList(Channel.create(HBO_HD_CHANNEL), Channel.create(HBO_2_HD_CHANNEL))))
                        )
                ),
                createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                createTvPackage(FAMILY_CYFROWY_POLSAT, asList(Channel.create(POLSAT), Channel.create(TVN))),
                                new LinkedList<>(),
                                asList(createTvPackage(HBO_HD_CHANNEL, asList(Channel.create(HBO_HD_CHANNEL), Channel.create(HBO_2_HD_CHANNEL))))
                        )
                )
        ));
        Criteria criteria = createCriteria(Collections.singletonList(Channel.create(ELEVEN_SPORTS_1)));
        ChannelCriteriaStrategy channelCriteriaStrategy = new ChannelCriteriaStrategy(criteria);

        List<ResultTvPackage> actual = channelCriteriaStrategy.getFilteredResult(resultTvPackage);

        List<ResultTvPackage> expected = Collections.singletonList(
                createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT,
                        new FilteredTvPackage(
                                createTvPackage(SMALL_FAMILY_CYFROWY_POLSAT, asList(Channel.create(ELEVEN_SPORTS_1), Channel.create(TVN))),
                                new LinkedList<>(),
                                asList(
                                        createTvPackage(MULTI_MAN_PACK, asList(Channel.create(DORCEL_TV_HD), Channel.create(BRAZZERS))),
                                        createTvPackage(HBO_HD_CHANNEL, asList(Channel.create(HBO_HD_CHANNEL), Channel.create(HBO_2_HD_CHANNEL)))
                                )
                        )
                )
        );
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldReturnElevenSportAndFoxPlay_whenOneExistInMainTvPackageAndSecondExistInExtraTvPackage() {
        List<ResultTvPackage> resultTvPackage = new ArrayList<>(asList(
                createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                createTvPackage(SMALL_FAMILY_CYFROWY_POLSAT,
                                        asList(Channel.create(ELEVEN_SPORTS_1), Channel.create(TVN))
                                ),
                                new ArrayList<>(),
                                asList(createTvPackage(MULTI_MAN_PACK, asList(Channel.create(DORCEL_TV_HD), Channel.create(BRAZZERS))), createTvPackage(FOX_PLAY, Collections.singletonList(Channel.create(FOX_PLAY))))
                        )
                ),
                createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                createTvPackage(FAMILY_CYFROWY_POLSAT, asList(Channel.create(POLSAT), Channel.create(TVN))),
                                new ArrayList<>(),
                                asList(createTvPackage(HBO_HD_CHANNEL, asList(Channel.create(HBO_HD_CHANNEL), Channel.create(HBO_2_HD_CHANNEL))))
                        )
                )
        ));
        Criteria criteria = createCriteria(asList(Channel.create(ELEVEN_SPORTS_1), Channel.create(FOX_PLAY)));
        ChannelCriteriaStrategy channelCriteriaStrategy = new ChannelCriteriaStrategy(criteria);

        List<ResultTvPackage> actual = channelCriteriaStrategy.getFilteredResult(resultTvPackage);

        List<ResultTvPackage> expected = Collections.singletonList(
                createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT,
                        new FilteredTvPackage(
                                createTvPackage(SMALL_FAMILY_CYFROWY_POLSAT, asList(Channel.create(ELEVEN_SPORTS_1), Channel.create(TVN))),
                                Collections.singletonList(createTvPackage(FOX_PLAY, Collections.singletonList(Channel.create(FOX_PLAY)))),
                                asList(
                                        createTvPackage(MULTI_MAN_PACK, asList(Channel.create(DORCEL_TV_HD), Channel.create(BRAZZERS))),
                                        createTvPackage(FOX_PLAY, Collections.singletonList(Channel.create(FOX_PLAY)))
                                )
                        )
                )
        );
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldReturnElevenSport_whenExistInTwoExtraTvPackages() {
        List<ResultTvPackage> resultTvPackage = new ArrayList<>(asList(
                createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                createTvPackage(SMALL_FAMILY_CYFROWY_POLSAT,
                                        asList(Channel.create(POLSAT), Channel.create(TVN))
                                ),
                                new LinkedList<>(),
                                asList(createTvPackage(MULTI_MAN_PACK, asList(Channel.create(ELEVEN_SPORTS_1), Channel.create(BRAZZERS))), createTvPackage(ELEVEN_SPORTS, Collections.singletonList(Channel.create(ELEVEN_SPORTS_1))))
                        )
                ),
                createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                createTvPackage(FAMILY_CYFROWY_POLSAT, asList(Channel.create(POLSAT), Channel.create(TVN))),
                                new LinkedList<>(),
                                asList(createTvPackage(HBO_HD_CHANNEL, asList(Channel.create(HBO_HD_CHANNEL), Channel.create(HBO_2_HD_CHANNEL))))
                        )
                )
        ));
        Criteria criteria = createCriteria(asList(Channel.create(ELEVEN_SPORTS_1)));
        ChannelCriteriaStrategy channelCriteriaStrategy = new ChannelCriteriaStrategy(criteria);

        List<ResultTvPackage> actual = channelCriteriaStrategy.getFilteredResult(resultTvPackage);

        List<ResultTvPackage> expected = Collections.singletonList(
                createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT,
                        new FilteredTvPackage(
                                createTvPackage(SMALL_FAMILY_CYFROWY_POLSAT, asList(Channel.create(POLSAT), Channel.create(TVN))),
                                asList(createTvPackage(MULTI_MAN_PACK, asList(Channel.create(ELEVEN_SPORTS_1), Channel.create(BRAZZERS))), createTvPackage(ELEVEN_SPORTS, Collections.singletonList(Channel.create(ELEVEN_SPORTS_1)))),
                                asList(
                                        createTvPackage(MULTI_MAN_PACK, asList(Channel.create(ELEVEN_SPORTS_1), Channel.create(BRAZZERS))),
                                        createTvPackage(ELEVEN_SPORTS, Collections.singletonList(Channel.create(ELEVEN_SPORTS_1)))
                                )

                        )
                )
        );
        assertThat(actual).isEqualTo(expected);
    }
}