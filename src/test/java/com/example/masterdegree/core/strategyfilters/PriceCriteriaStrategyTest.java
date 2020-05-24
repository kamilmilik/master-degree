package com.example.masterdegree.core.strategyfilters;

import com.example.masterdegree.models.model.Criteria;
import com.example.masterdegree.models.model.filter.FilteredTvPackage;
import com.example.masterdegree.models.model.filter.ResultTvPackage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static com.example.masterdegree.Constants.*;
import static com.example.masterdegree.DataCreationUtils.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class PriceCriteriaStrategyTest {

    private PriceCriteriaStrategy priceCriteriaStrategy;

    private static Stream<Arguments> price() {
        List<ResultTvPackage> resultTvPackages = new ArrayList<>(asList(
                createResultTvPackage(CANAL_PLUS_ID, CANAL_PLUS, new FilteredTvPackage(
                                createTvPackage(20d),
                                new LinkedList<>(),
                                asList(createTvPackage(40d), createTvPackage(50d))
                        )
                ),
                createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT_ID, new FilteredTvPackage(
                                createTvPackage(30d),
                                new LinkedList<>(),
                                asList(createTvPackage(40d), createTvPackage(50d))
                        )
                ),
                createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT_ID, new FilteredTvPackage(
                                createTvPackage(40d),
                                new LinkedList<>(),
                                asList(createTvPackage(40d), createTvPackage(50d))
                        )
                )
        ));

        Criteria criteria_1 = createCriteria(10d);
        Criteria criteria_2 = createCriteria(20d);
        Criteria criteria_3 = createCriteria(30d);
        Criteria criteria_4 = createCriteria(40d);
        Criteria criteria_5 = createCriteria(39.99d);
        Criteria criteria_6 = createCriteria(40.01d);

        List<ResultTvPackage> expected_1 = new LinkedList<>();
        List<ResultTvPackage> expected_2 = Collections.singletonList(
                createResultTvPackage(CANAL_PLUS_ID, CANAL_PLUS, new FilteredTvPackage(
                                createTvPackage(20d),
                                new LinkedList<>(),
                                asList(createTvPackage(40d), createTvPackage(50d))
                        )
                )
        );
        List<ResultTvPackage> expected_3 = asList(
                createResultTvPackage(CANAL_PLUS_ID, CANAL_PLUS, new FilteredTvPackage(
                                createTvPackage(20d),
                                new LinkedList<>(),
                                asList(createTvPackage(40d), createTvPackage(50d))
                        )
                ),
                createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT_ID, new FilteredTvPackage(
                                createTvPackage(30d),
                                new LinkedList<>(),
                                asList(createTvPackage(40d), createTvPackage(50d))
                        )
                )
        );
        List<ResultTvPackage> expected_4 = asList(
                createResultTvPackage(CANAL_PLUS_ID, CANAL_PLUS, new FilteredTvPackage(
                                createTvPackage(20d),
                                new LinkedList<>(),
                                asList(createTvPackage(40d), createTvPackage(50d))
                        )
                ),
                createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT_ID, new FilteredTvPackage(
                                createTvPackage(30d),
                                new LinkedList<>(),
                                asList(createTvPackage(40d), createTvPackage(50d))
                        )
                ),
                createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT_ID, new FilteredTvPackage(
                                createTvPackage(40d),
                                new LinkedList<>(),
                                asList(createTvPackage(40d), createTvPackage(50d))
                        )
                )
        );
        return Stream.of(
                Arguments.of(resultTvPackages, criteria_1, expected_1),
                Arguments.of(resultTvPackages, criteria_2, expected_2),
                Arguments.of(resultTvPackages, criteria_3, expected_3),
                Arguments.of(resultTvPackages, criteria_4, expected_4),
                Arguments.of(resultTvPackages, criteria_5, expected_3),
                Arguments.of(resultTvPackages, criteria_6, expected_4)
        );
    }

    @ParameterizedTest
    @MethodSource("price")
    void shouldReturnResultFilteredByPriceInMainTvPackages(List<ResultTvPackage> input, Criteria criteria, List<ResultTvPackage> expected) {
        priceCriteriaStrategy = new PriceCriteriaStrategy(criteria);

        List<ResultTvPackage> actual = priceCriteriaStrategy.getFilteredResult(input);

        assertThat(actual).isEqualTo(expected);
    }
}