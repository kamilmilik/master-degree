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

class OperatorCriteriaStrategyTest {

    private static Stream<Arguments> operators() {
        List<ResultTvPackage> resultTvPackages = new ArrayList<>(asList(
                createResultTvPackage(CANAL_PLUS_ID, CANAL_PLUS, new FilteredTvPackage(
                                createTvPackage(CANAL_PLUS_START),
                                new LinkedList<>(),
                                asList(createTvPackage(HBO_HD_CHANNEL), createTvPackage(MULTI_MAN_PACK))
                        )
                ),
                createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                createTvPackage(FAMILY_CYFROWY_POLSAT),
                                new LinkedList<>(),
                                Collections.singletonList(createTvPackage(HBO_HD_CHANNEL))
                        )
                )
        ));

        Criteria criteriaOneOperator = createCriteriaOperators(Collections.singletonList(CYFROWY_POLSAT_ID));
        Criteria criteriaManyOperators = createCriteriaOperators(asList(CYFROWY_POLSAT_ID, CANAL_PLUS_ID));
        Criteria criteriaNoneOperators = createCriteriaOperators(new LinkedList<>());
        Criteria criteriaNotMeetOperators = createCriteriaOperators(asList("1221344", "123213"));
        Criteria criteriaOneOperator_2 = createCriteriaOperators(asList("1221344", CYFROWY_POLSAT_ID));

        List<ResultTvPackage> expectedOneOperator = Collections.singletonList(
                createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                createTvPackage(FAMILY_CYFROWY_POLSAT),
                                new LinkedList<>(),
                                Collections.singletonList(createTvPackage(HBO_HD_CHANNEL))
                        )
                )
        );
        List<ResultTvPackage> expectedManyOperators = asList(
                createResultTvPackage(CANAL_PLUS_ID, CANAL_PLUS, new FilteredTvPackage(
                                createTvPackage(CANAL_PLUS_START),
                                new LinkedList<>(),
                                asList(createTvPackage(HBO_HD_CHANNEL), createTvPackage(MULTI_MAN_PACK))
                        )
                ),
                createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                createTvPackage(FAMILY_CYFROWY_POLSAT),
                                new LinkedList<>(),
                                Collections.singletonList(createTvPackage(HBO_HD_CHANNEL))
                        )
                )
        );
        List<ResultTvPackage> expectedEmptyResults = new LinkedList<>();
        return Stream.of(
                Arguments.of(resultTvPackages, criteriaOneOperator, expectedOneOperator),
                Arguments.of(resultTvPackages, criteriaManyOperators, expectedManyOperators),
                Arguments.of(resultTvPackages, criteriaNoneOperators, expectedManyOperators),
                Arguments.of(resultTvPackages, criteriaNotMeetOperators, expectedEmptyResults),
                Arguments.of(resultTvPackages, criteriaOneOperator_2, expectedOneOperator)
        );
    }

    @ParameterizedTest
    @MethodSource("operators")
    void shouldReturnResultBySelectedOperators(List<ResultTvPackage> input, Criteria criteria, List<ResultTvPackage> expected) {
        OperatorCriteriaStrategy operatorCriteriaStrategy = new OperatorCriteriaStrategy(criteria);

        List<ResultTvPackage> actual = operatorCriteriaStrategy.getFilteredResult(input);

        assertThat(actual).isEqualTo(expected);
    }

}