package com.example.masterdegree.core.strategyfilters;

import com.example.masterdegree.models.model.Channel;
import com.example.masterdegree.models.model.Criteria;
import com.example.masterdegree.models.model.TvPackage;
import com.example.masterdegree.models.model.filter.FilteredTvPackage;
import com.example.masterdegree.models.model.filter.ResultTvPackage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static com.example.masterdegree.Constants.*;
import static com.example.masterdegree.core.strategyfilters.DataCreationUtils.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class ChannelAndPriceCombinationCriteriaStrategyTest {

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class ResultFilteredByChannelsCombinationAndPrice {

        @ParameterizedTest
        @MethodSource("provideDataForNotMeetCriteriaTvPackages")
        @DisplayName("Test not returning filtered result when tv packages not meet criteria")
        void shouldNotReturnFilteredResult_whenTvPackagesNotMeetCriteria(List<ResultTvPackage> input, List<ResultTvPackage> expected) {
            Criteria criteria = createCriteria(60d, asList(Channel.create(DORCEL_TV_HD), Channel.create(BRAZZERS), Channel.create(FOX_PLAY)));
            ChannelAndPriceCombinationCriteriaStrategy criteriaStrategy = new ChannelAndPriceCombinationCriteriaStrategy(criteria);
            List<ResultTvPackage> actual = criteriaStrategy.getFilteredResult(input);
            assertThat(actual).isEqualTo(expected);
        }

        Stream<Arguments> provideDataForNotMeetCriteriaTvPackages() {
            TvPackage activeTvPackage = createTvPackage(ACTIVE_FAMILY, 30d, asList(Channel.create(DORCEL_TV_HD), Channel.create(BRAZZERS), Channel.create(FOX_PLAY), Channel.create(ACTIVE_FAMILY)));
            List<ResultTvPackage> inputNotMeetAllCriteriaOneExtraTvPackage = new ArrayList<>(asList(
                    createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                    createTvPackage(FAMILY_CYFROWY_POLSAT, 50d, asList(Channel.create(POLSAT), Channel.create(TVN))),
                                    asList(activeTvPackage),
                                    asList(activeTvPackage)
                            )
                    )
            ));
            List<ResultTvPackage> expectedNotMeetAllCriteriaOneExtraTvPackage = new ArrayList<>();

            TvPackage hboTvPackage = createTvPackage(HBO, 15d, Collections.singletonList(Channel.create(HBO)));
            TvPackage foxTvPackage = createTvPackage(FOX_PLAY, 5d, Collections.singletonList(Channel.create(FOX_PLAY)));
            TvPackage dorcelTvPackage = createTvPackage(DORCEL_TV_HD, 2d, Collections.singletonList(Channel.create(DORCEL_TV_HD)));
            List<ResultTvPackage> inputNotMeetAllCriteriaManyExtraTvPackages = new ArrayList<>(asList(
                    createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                    createTvPackage(FAMILY_CYFROWY_POLSAT, 50d, asList(Channel.create(POLSAT), Channel.create(TVN))),
                                    asList(hboTvPackage, foxTvPackage, dorcelTvPackage),
                                    asList(hboTvPackage, foxTvPackage, dorcelTvPackage)
                            )
                    )
            ));
            List<ResultTvPackage> expectedNotMeetAllCriteriaManyExtraTvPackages = new ArrayList<>();

            return Stream.of(
                    Arguments.of(inputNotMeetAllCriteriaOneExtraTvPackage, expectedNotMeetAllCriteriaOneExtraTvPackage),
                    Arguments.of(inputNotMeetAllCriteriaManyExtraTvPackages, expectedNotMeetAllCriteriaManyExtraTvPackages)
            );
        }

        @ParameterizedTest
        @MethodSource("provideDataForMeetCriteriaTvPackages")
        @DisplayName("Test returning filtered result when tv packages meet criteria")
        void shouldReturnFilteredResult_whenTvPackagesMeetCriteria(Criteria criteria, List<ResultTvPackage> input, List<ResultTvPackage> expected) {
            ChannelAndPriceCombinationCriteriaStrategy criteriaStrategy = new ChannelAndPriceCombinationCriteriaStrategy(criteria);
            List<ResultTvPackage> actual = criteriaStrategy.getFilteredResult(input);
            assertThat(actual).isEqualTo(expected);
        }

        Stream<Arguments> provideDataForMeetCriteriaTvPackages() {
            Criteria criteriaChannels = createCriteria(400d, asList(Channel.create(DORCEL_TV_HD), Channel.create(BRAZZERS), Channel.create(FOX_PLAY)));
            TvPackage multiTvPackage = createTvPackage(MULTI_MAN_PACK, 10d, asList(Channel.create(DORCEL_TV_HD), Channel.create(BRAZZERS), Channel.create(FOX_PLAY), Channel.create(FIGHTBOX)));
            TvPackage foxTvPackage = createTvPackage(FOX_PLAY, 5d, Collections.singletonList(Channel.create(FOX_PLAY)));
            TvPackage dorcelTvPackage = createTvPackage(DORCEL_TV_HD, 2d, Collections.singletonList(Channel.create(DORCEL_TV_HD)));
            TvPackage brazzersTvPackage = createTvPackage(BRAZZERS, 1d, asList(Channel.create(BRAZZERS), Channel.create(BBC)));
            TvPackage hboTvPackage = createTvPackage(HBO, 15d, Collections.singletonList(Channel.create(HBO)));
            List<ResultTvPackage> inputManyExtraMeetCriteriaCheapest = new ArrayList<>(asList(
                    createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                    createTvPackage(SMALL_FAMILY_CYFROWY_POLSAT, 40d, asList(Channel.create(ELEVEN_SPORTS_1), Channel.create(TVN))),
                                    asList(multiTvPackage, foxTvPackage, dorcelTvPackage, brazzersTvPackage),
                                    new ArrayList<>(asList(multiTvPackage, foxTvPackage, dorcelTvPackage, brazzersTvPackage, hboTvPackage))
                            )
                    )
            ));

            List<ResultTvPackage> expectedManyExtraMeetCriteriaCheapest = new ArrayList<>(asList(
                    createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                    createTvPackage(SMALL_FAMILY_CYFROWY_POLSAT, 40d, asList(Channel.create(ELEVEN_SPORTS_1), Channel.create(TVN))),
                                    asList(foxTvPackage, dorcelTvPackage, brazzersTvPackage),
                                    new ArrayList<>(asList(multiTvPackage, hboTvPackage))
                            )
                    )
            ));

            TvPackage activeTvPackage = createTvPackage(ACTIVE_FAMILY, 14d, asList(Channel.create(DORCEL_TV_HD), Channel.create(BRAZZERS), Channel.create(FOX_PLAY), Channel.create(ACTIVE_FAMILY)));
            TvPackage adventureTvPackage = createTvPackage(ADVENTURE, 10d, asList(Channel.create(DORCEL_TV_HD), Channel.create(BRAZZERS), Channel.create(ADVENTURE)));
            foxTvPackage = createTvPackage(FOX_PLAY, 5d, Collections.singletonList(Channel.create(FOX_PLAY)));
            hboTvPackage = createTvPackage(HBO, 15d, Collections.singletonList(Channel.create(HBO)));
            List<ResultTvPackage> inputOneExtraMeetAllCriteriaCheapest = new ArrayList<>(asList(
                    createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                    createTvPackage(FAMILY_CYFROWY_POLSAT, 50d, asList(Channel.create(POLSAT), Channel.create(TVN))),
                                    asList(activeTvPackage, adventureTvPackage, foxTvPackage),
                                    new ArrayList<>(asList(activeTvPackage, adventureTvPackage, foxTvPackage, hboTvPackage))
                            )
                    )
            ));

            List<ResultTvPackage> expectedOneExtraMeetAllCriteriaCheapest = new ArrayList<>(asList(
                    createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                    createTvPackage(FAMILY_CYFROWY_POLSAT, 50d, asList(Channel.create(POLSAT), Channel.create(TVN))),
                                    asList(activeTvPackage),
                                    new ArrayList<>(asList(adventureTvPackage, hboTvPackage))
                            )
                    )
            ));
            return Stream.of(
                    Arguments.of(criteriaChannels, inputManyExtraMeetCriteriaCheapest, expectedManyExtraMeetCriteriaCheapest),
                    Arguments.of(criteriaChannels, inputOneExtraMeetAllCriteriaCheapest, expectedOneExtraMeetAllCriteriaCheapest)
            );
        }
    }


    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class CombinedTvPackagesContainsAllChannelsTest {

        @ParameterizedTest
        @MethodSource("provideDataCreateCombinedTvPackage")
        void shouldCreateCombinedTvPackage_whenHasTvPackageWhichMeetCriteria(List<TvPackage> input, int expectedSizeIsNewtonSymbolCombination) {
            ChannelAndPriceCombinationCriteriaStrategy channelAndPriceCombinationCriteriaStrategy = new ChannelAndPriceCombinationCriteriaStrategy(null);
            List<List<TvPackage>> output = channelAndPriceCombinationCriteriaStrategy.createAllCombinationsFromGivenList(input);
            int actualSize = output.size();
            assertThat(actualSize).isEqualTo(expectedSizeIsNewtonSymbolCombination);
        }

        Stream<Arguments> provideDataCreateCombinedTvPackage() {
            return Stream.of(
                    Arguments.of(asList(createTvPackage(HBO), createTvPackage(ELEVEN_SPORTS), createTvPackage(ADVENTURE)), 7),
                    Arguments.of(asList(createTvPackage(HBO), createTvPackage(ELEVEN_SPORTS), createTvPackage(ADVENTURE), createTvPackage(HUSTLER_HD)), 15)
            );
        }

        @ParameterizedTest
        @MethodSource("provideDataForCombinedTvPackagesNotContainsAllCriteriaChannels")
        @DisplayName("Test returning nothing when combinations not contains all criteria channels")
        void shouldReturnNothing_whenCombinedTvPackagesNotContainsAllCriteriaChannels(Criteria criteria, ResultTvPackage inputResult,List<List<TvPackage>> input, List<List<TvPackage>> expected) {
            ChannelAndPriceCombinationCriteriaStrategy criteriaStrategy = new ChannelAndPriceCombinationCriteriaStrategy(criteria);
            List<List<TvPackage>> actual = criteriaStrategy.checkIfCombinedTvPackagesContainsAllCriteriaChannelsExceptChannelsFromMainTvPackage(inputResult, input);
            assertThat(actual).isEqualTo(expected);
        }

        @ParameterizedTest
        @MethodSource("provideDataForCombinedTvPackagesWhereSomeCombinationsContainsAllChannels")
        @DisplayName("Test returning combined tv packages when some combinations contains all criteria channels")
        void shouldReturnCombinedTvPackages_whenCombinedTvPackagesWhereTwoCombinationsContainsAllCriteriaChannels(Criteria criteria, ResultTvPackage inputResult, List<List<TvPackage>> input, List<List<TvPackage>> expected) {
            ChannelAndPriceCombinationCriteriaStrategy criteriaStrategy = new ChannelAndPriceCombinationCriteriaStrategy(criteria);
            List<List<TvPackage>> actual = criteriaStrategy.checkIfCombinedTvPackagesContainsAllCriteriaChannelsExceptChannelsFromMainTvPackage(inputResult, input);
            assertThat(actual).isEqualTo(expected);
        }

        Stream<Arguments> provideDataForCombinedTvPackagesWhereSomeCombinationsContainsAllChannels() {
            Criteria criteria = createCriteria(asList(Channel.create(HBO), Channel.create(ELEVEN_SPORTS_1), Channel.create(ADVENTURE), Channel.create(POLSAT)));
            ResultTvPackage inputResultNotImportantExtraAndMeetTvPackages = createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                    createTvPackage(FAMILY_CYFROWY_POLSAT, 50d, asList(Channel.create(POLSAT), Channel.create(TVN))),
                    new ArrayList<>(),
                    new ArrayList<>()
            ));
            List<TvPackage> combinedTvPackagesContainsAllCriteriaChannelsSingleTvPackage = asList(
                    createTvPackage(asList(Channel.create(HBO), Channel.create(ELEVEN_SPORTS_1), Channel.create(ADVENTURE), Channel.create(BBC)))
            );
            List<TvPackage> combinedTvPackagesContainsAllCriteriaChannelsMultipleTvPackages = asList(
                    createTvPackage(asList(Channel.create(HBO), Channel.create(ELEVEN_SPORTS_1), Channel.create(BBC))),
                    createTvPackage(asList(Channel.create(ADVENTURE)))
            );
            List<TvPackage> combinedTvPackagesNotContainsAllCriteriaChannels = asList(
                    createTvPackage(asList(Channel.create(HBO))),
                    createTvPackage(asList(Channel.create(ELEVEN_SPORTS_1), Channel.create(POLSAT)))
            );

            return Stream.of(Arguments.of(
                    criteria,
                    inputResultNotImportantExtraAndMeetTvPackages,
                    new ArrayList<>(asList(combinedTvPackagesContainsAllCriteriaChannelsSingleTvPackage, combinedTvPackagesContainsAllCriteriaChannelsMultipleTvPackages, combinedTvPackagesNotContainsAllCriteriaChannels)),
                    new ArrayList<>(asList(combinedTvPackagesContainsAllCriteriaChannelsSingleTvPackage, combinedTvPackagesContainsAllCriteriaChannelsMultipleTvPackages))
            ));
        }

        Stream<Arguments> provideDataForCombinedTvPackagesNotContainsAllCriteriaChannels() {
            Criteria criteria = createCriteria(asList(Channel.create(HBO), Channel.create(ELEVEN_SPORTS_1), Channel.create(ADVENTURE), Channel.create(POLSAT_SPORT_PREMIUM)));
            ResultTvPackage inputResultNotImportantExtraAndMeetTvPackages = createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                    createTvPackage(FAMILY_CYFROWY_POLSAT, 50d, asList(Channel.create(POLSAT), Channel.create(TVN))),
                    new ArrayList<>(),
                    new ArrayList<>()
            ));

            return Stream.of(Arguments.of(
                    criteria,
                    inputResultNotImportantExtraAndMeetTvPackages,
                    new ArrayList<>(asList(
                            asList(createTvPackage(asList(Channel.create(HBO), Channel.create(ELEVEN_SPORTS_1), Channel.create(ADVENTURE)))),
                            asList(createTvPackage(asList(Channel.create(HBO))), createTvPackage(asList(Channel.create(ELEVEN_SPORTS_1), Channel.create(POLSAT))))
                    )),
                    new ArrayList<>()
            ));
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    public class SortingTvPackagesBySumOfPriceTest {

        @ParameterizedTest
        @DisplayName("Test returning sorted combined tv packages")
        @MethodSource("providerForCombinedTvPackagesWithoutOrderBySumOfPrice")
        public void shouldReturnSorted_whenCombinedTvPackagesAreNotSortedBySumOfPrice(List<List<TvPackage>> input, List<TvPackage> expected) {
            ChannelAndPriceCombinationCriteriaStrategy criteriaStrategy = new ChannelAndPriceCombinationCriteriaStrategy(null);
            List<TvPackage> actual = criteriaStrategy.sortAndGetCombinedTvPackagesBySumOfPrice(input);
            Assertions.assertThat(actual).isEqualTo(expected);
        }

        Stream<Arguments> providerForCombinedTvPackagesWithoutOrderBySumOfPrice() {
            return Stream.of(Arguments.of(
                    asList(
                            asList(createTvPackage(10), createTvPackage(50)),
                            asList(createTvPackage(12), createTvPackage(14), createTvPackage(40)),
                            asList(createTvPackage(12), createTvPackage(3), createTvPackage(40)),
                            asList(createTvPackage(12), createTvPackage(3), createTvPackage(4), createTvPackage(4))
                    ),
                    asList(createTvPackage(12), createTvPackage(3), createTvPackage(4), createTvPackage(4))
                    )
            );
        }

    }

    @Nested
    public class CombinedTvPackagesSumOfPriceTest {
        @Test
        @DisplayName("Test returning nothing when sum of price is not less criteria price")
        public void shouldReturnNothing_whenSumOfTvPackagesPriceWithMainTvPackageIsNotLessPriceCriteria() {
            List<List<TvPackage>> combinedTvPackages = asList(
                    asList(createTvPackage(10), createTvPackage(50)),
                    asList(createTvPackage(46), createTvPackage(5)),
                    asList(createTvPackage(12), createTvPackage(14), createTvPackage(40))
            );
            Criteria criteria = createCriteria(50);
            ChannelAndPriceCombinationCriteriaStrategy criteriaStrategy = new ChannelAndPriceCombinationCriteriaStrategy(criteria);
            List<List<TvPackage>> actual = criteriaStrategy.getFilteredCombinationsWhichSumOfPricePlusGivenPriceIsBetweenPriceCriteria(45d, combinedTvPackages);
            List<List<TvPackage>> expected = new ArrayList<>();
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("Test returning multiple combined tv packages with sum of price less criteria price")
        public void shouldReturnCombinedTvPackages_whenSumOfTvPackagesWithMainTvPackageIsLessPriceCriteria() {
            List<List<TvPackage>> combinedTvPackages = asList(
                    asList(createTvPackage(2), createTvPackage(10)),
                    asList(createTvPackage(30), createTvPackage(20)),
                    asList(createTvPackage(5), createTvPackage(15)),
                    asList(createTvPackage(25), createTvPackage(20)),
                    asList(createTvPackage(12), createTvPackage(14), createTvPackage(50))
            );
            Criteria criteria = createCriteria(50d);
            ChannelAndPriceCombinationCriteriaStrategy criteriaStrategy = new ChannelAndPriceCombinationCriteriaStrategy(criteria);
            List<List<TvPackage>> expected = asList(
                    asList(createTvPackage(2), createTvPackage(10)),
                    asList(createTvPackage(5), createTvPackage(15)),
                    asList(createTvPackage(25), createTvPackage(20))
            );

            List<List<TvPackage>> actual = (criteriaStrategy.getFilteredCombinationsWhichSumOfPricePlusGivenPriceIsBetweenPriceCriteria(5d, combinedTvPackages));
            Assertions.assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class RemoveCollectionsTest {

        @ParameterizedTest
        @MethodSource("provideDataForRemoveAllTvPackagesFromAvailableExtraTvPackages")
        void shouldRemoveAllTvPackagesFromAvailableExtraTvPackages_whenTvPackagesAreInTvPackagesWhichMeetCriteria(List<ResultTvPackage> input, List<ResultTvPackage> expected) {
            ChannelAndPriceCombinationCriteriaStrategy criteriaStrategy = new ChannelAndPriceCombinationCriteriaStrategy(null);
            List<ResultTvPackage> actual = criteriaStrategy.removeExtraAvailableTvPackagesWhichAreInMeetCriteriaPackages(input);
            Assertions.assertThat(actual).isEqualTo(expected);
        }

        Stream<Arguments> provideDataForRemoveAllTvPackagesFromAvailableExtraTvPackages() {
            List<ResultTvPackage> input = new ArrayList<>(asList(
                    createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                            createTvPackage(FAMILY_CYFROWY_POLSAT, 50d, asList(Channel.create(POLSAT), Channel.create(TVN))),
                            asList(createTvPackage(HBO, asList(Channel.create(HBO)))),
                            new ArrayList<>(asList(
                                    createTvPackage(HBO, asList(Channel.create(HBO))),
                                    createTvPackage(FIGHTBOX, asList(Channel.create(FIGHTBOX)))
                            )
                            ))
                    )
            ));
            List<ResultTvPackage> expected = new ArrayList<>(asList(
                    createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                    createTvPackage(FAMILY_CYFROWY_POLSAT, 50d, asList(Channel.create(POLSAT), Channel.create(TVN))),
                                    asList(createTvPackage(HBO, asList(Channel.create(HBO)))),
                                    new ArrayList<>(asList(createTvPackage(FIGHTBOX, asList(Channel.create(FIGHTBOX)))))
                            )
                    )
            ));
            List<ResultTvPackage> inputEmpty = new ArrayList<>(asList(
                    createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                    createTvPackage(FAMILY_CYFROWY_POLSAT, 50d, asList(Channel.create(POLSAT), Channel.create(TVN))),
                                    new LinkedList<>(),
                                    new ArrayList<>(asList(createTvPackage(HBO, asList(Channel.create(HBO)))))
                            )
                    )
            ));
            List<ResultTvPackage> expectedEmpty = new ArrayList<>(asList(
                    createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                    createTvPackage(FAMILY_CYFROWY_POLSAT, 50d, asList(Channel.create(POLSAT), Channel.create(TVN))),
                                    new LinkedList<>(),
                                    new ArrayList<>(asList(createTvPackage(HBO, asList(Channel.create(HBO)))))
                            )
                    )
            ));

            List<ResultTvPackage> inputAllChannelsInExtra = new ArrayList<>(asList(
                    createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                    createTvPackage(FAMILY_CYFROWY_POLSAT, 50d, asList(Channel.create(POLSAT), Channel.create(TVN))),
                                    asList(
                                            createTvPackage(HBO, asList(Channel.create(HBO)))
                                    ),
                                    new LinkedList<>(asList(
                                            createTvPackage(HBO, asList(Channel.create(HBO))),
                                            createTvPackage(HBO_GO, asList(Channel.create(HBO), Channel.create(HBO_GO))),
                                            createTvPackage(FIGHTBOX, asList(Channel.create(FIGHTBOX)))

                                    ))
                            )
                    )
            ));
            List<ResultTvPackage> expectedAllChannelsInExtra = new ArrayList<>(asList(
                    createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                    createTvPackage(FAMILY_CYFROWY_POLSAT, 50d, asList(Channel.create(POLSAT), Channel.create(TVN))),
                                    asList(
                                            createTvPackage(HBO, asList(Channel.create(HBO)))
                                    ),
                                    new LinkedList<>(asList(
                                            createTvPackage(HBO_GO, asList(Channel.create(HBO), Channel.create(HBO_GO))),
                                            createTvPackage(FIGHTBOX, asList(Channel.create(FIGHTBOX)))
                                    ))
                            )
                    )
            ));

            List<ResultTvPackage> inputAllChannelsInMeetCriteria = new ArrayList<>(asList(
                    createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                    createTvPackage(FAMILY_CYFROWY_POLSAT, 50d, asList(Channel.create(POLSAT), Channel.create(TVN))),
                                    asList(
                                            createTvPackage(HBO_GO, asList(Channel.create(HBO), Channel.create(HBO_GO)))
                                    ),
                                    new LinkedList<>(asList(
                                            createTvPackage(HBO, asList(Channel.create(HBO))),
                                            createTvPackage(HBO_GO, asList(Channel.create(HBO), Channel.create(HBO_GO))),
                                            createTvPackage(FIGHTBOX, asList(Channel.create(FIGHTBOX)))

                                    ))
                            )
                    )
            ));
            List<ResultTvPackage> expectedAllChannelsInMeetCriteria = new ArrayList<>(asList(
                    createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                    createTvPackage(FAMILY_CYFROWY_POLSAT, 50d, asList(Channel.create(POLSAT), Channel.create(TVN))),
                                    asList(
                                            createTvPackage(HBO_GO, asList(Channel.create(HBO), Channel.create(HBO_GO)))
                                    ),
                                    new LinkedList<>(asList(createTvPackage(FIGHTBOX, asList(Channel.create(FIGHTBOX)))))
                            )
                    )
            ));

            return Stream.of(
                    Arguments.of(input, expected),
                    Arguments.of(inputEmpty, expectedEmpty),
                    Arguments.of(inputAllChannelsInExtra, expectedAllChannelsInExtra),
                    Arguments.of(inputAllChannelsInMeetCriteria, expectedAllChannelsInMeetCriteria)
            );
        }
    }

}