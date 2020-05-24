package com.example.masterdegree.core.strategyfilters;

import com.example.masterdegree.models.model.Criteria;
import com.example.masterdegree.models.model.Term;
import com.example.masterdegree.models.model.filter.FilteredTvPackage;
import com.example.masterdegree.models.model.filter.ResultTvPackage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static com.example.masterdegree.Constants.*;
import static com.example.masterdegree.DataCreationUtils.createResultTvPackage;
import static com.example.masterdegree.DataCreationUtils.createTvPackage;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class TermCriteriaStrategyTest {

    @ParameterizedTest
    @MethodSource("provideDataForTermResult")
    void shouldReturnFilteredByTermResult(Criteria criteria, List<ResultTvPackage> input, List<ResultTvPackage> expected) {
        TermCriteriaStrategy termCriteriaStrategy = new TermCriteriaStrategy(criteria);

        List<ResultTvPackage> actual = termCriteriaStrategy.getFilteredResult(input);
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideDataForTermResult() {
        Criteria criteriaTerm12 = createCriteria(TERM_12);
        List<ResultTvPackage> inputTerm = new ArrayList<>(asList(
                createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                createTvPackage(ACTIVE_FAMILY, TERM_12),
                                new ArrayList<>(),
                                new ArrayList<>()
                        )
                ),
                createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                createTvPackage(COMFORT, TERM_6),
                                new ArrayList<>(),
                                new ArrayList<>()
                        )
                )));

        List<ResultTvPackage> expectedTerm = new ArrayList<>(Collections.singletonList(
                createResultTvPackage(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, new FilteredTvPackage(
                                createTvPackage(ACTIVE_FAMILY, TERM_12),
                                new ArrayList<>(),
                                new ArrayList<>()
                        )
                )
        ));

        List<ResultTvPackage> expectedEmpty = new ArrayList<>();
        List<ResultTvPackage> expectedAllPackages = new ArrayList<>(inputTerm);

        Criteria criteriaTerm24 = createCriteria(TERM_24);
        Criteria criteriaNoTerm = createCriteria(NO_TERM);
        return Stream.of(
                Arguments.of(criteriaTerm12, inputTerm, expectedTerm),
                Arguments.of(criteriaTerm24, inputTerm, expectedEmpty),
                Arguments.of(criteriaNoTerm, inputTerm, expectedAllPackages)
        );
    }

    public static Criteria createCriteria(String term) {
        return Criteria.newCriteria(null, null, null, new Term(term));
    }

}