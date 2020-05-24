package com.example.masterdegree.models.mappers;

import com.example.masterdegree.models.dto.OperatorRequestDto;
import com.example.masterdegree.models.model.Channel;
import com.example.masterdegree.models.model.Operator;
import com.example.masterdegree.models.model.TvPackage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static com.example.masterdegree.Constants.*;
import static com.example.masterdegree.DataCreationUtils.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class OperatorMapperTest {

    private static OperatorMapper operatorMapper;

    private static Stream<Arguments> operators() {
        List<Channel> channels = asList(Channel.channel(TVN), Channel.channel(POLSAT));
        List<TvPackage> extraTvPackages = asList(createTvPackage(ELEVEN_SPORTS), createTvPackage(HBO));
        Operator operatorCanal = createOperator(CANAL_PLUS_ID, CANAL_PLUS, Collections.singletonList(
                createMainTvPackage(COMFORT, channels, extraTvPackages)
        ));

        OperatorRequestDto expected = new OperatorRequestDto(CANAL_PLUS_ID, CANAL_PLUS, "");
        return Stream.of(
                Arguments.of(operatorCanal, expected)
        );
    }

    @BeforeAll
    static void setup() {
        operatorMapper = new OperatorMapper(new ModelMapper());
    }

    @ParameterizedTest
    @MethodSource("operators")
    void shouldConvertToDto(Operator operator, OperatorRequestDto expected) {
        OperatorRequestDto actual = operatorMapper.convertToDto(operator);

        assertThat(actual).isEqualTo(expected);
    }
}