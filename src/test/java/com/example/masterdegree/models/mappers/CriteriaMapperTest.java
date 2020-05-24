package com.example.masterdegree.models.mappers;

import com.example.masterdegree.models.dto.CriteriaRequestDto;
import com.example.masterdegree.models.model.Criteria;
import com.example.masterdegree.models.model.Price;
import com.example.masterdegree.models.model.Term;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.example.masterdegree.Constants.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class CriteriaMapperTest {

    private static CriteriaMapper criteriaMapper;

    @BeforeAll
    public static void setup() {
        criteriaMapper = new CriteriaMapper();
    }

    private static Stream<Arguments> criteria() {
        CriteriaRequestDto criteriaRequestDto = new CriteriaRequestDto(asList(CANAL_PLUS_ID, CYFROWY_POLSAT_ID), 20d, asList(TVN, ELEVEN_SPORTS_2), TERM_24);

        Criteria expected = Criteria.newCriteria(asList(CANAL_PLUS_ID, CYFROWY_POLSAT_ID), new Price(20d), asList(TVN, ELEVEN_SPORTS_2), new Term(TERM_24));

        return Stream.of(
                Arguments.of(criteriaRequestDto, expected)
        );
    }

    @ParameterizedTest
    @MethodSource("criteria")
    void shouldConvertToEntity(CriteriaRequestDto input, Criteria expected) {
        Criteria actual = criteriaMapper.convertToEntity(input);

        assertThat(actual).isEqualTo(expected);
    }

}