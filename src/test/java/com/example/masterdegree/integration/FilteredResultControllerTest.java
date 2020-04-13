package com.example.masterdegree.integration;

import com.example.masterdegree.MasterDegreeApplication;
import com.example.masterdegree.controllers.FilteredResultController;
import com.example.masterdegree.models.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.masterdegree.Constants.*;
import static com.example.masterdegree.core.strategyfilters.DataCreationUtils.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

//import static org.hamcrest.MatcherAssert.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {MasterDegreeApplication.class, FilteredResultController.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FilteredResultControllerTest {

    private static final String URI = "/api/result";
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    @LocalServerPort
    private int port;
    @Autowired
    private ObjectMapper objectMapper;

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void shouldReturnFilteredResult(CriteriaRequestDto criteria, ResultTvPackagesResponseDto expected) throws JsonProcessingException {
        HttpEntity<CriteriaRequestDto> entity = new HttpEntity<>(criteria, headers);

        ResponseEntity<String> response = this.restTemplate
                .postForEntity(createURLWithPort(URI), entity, String.class);

        ResultTvPackagesResponseDto actual = objectMapper.readValue(response.getBody(), ResultTvPackagesResponseDto.class);

        List<String> expectedOperatorsId = expected.getResultTvPackages().stream().map(ResultTvPackageResponseDto::getOperatorId).collect(Collectors.toList());
        List<String> expectedOperatorsName = expected.getResultTvPackages().stream().map(ResultTvPackageResponseDto::getOperatorName).collect(Collectors.toList());
        Object[] expectedExtraAvailableTvPackageNames = expected.getResultTvPackages().stream().
                flatMap(resultTvPackageResponseDto -> resultTvPackageResponseDto.getFilteredTvPackage().getExtraAvailableTvPackages().stream())
                .map(TvPackageResponseDto::getName)
                .toArray();
        Object[] expectedMeetCriteriaTvPackageNames = expected.getResultTvPackages().stream().
                flatMap(resultTvPackageResponseDto -> resultTvPackageResponseDto.getFilteredTvPackage().getExtraTvPackagesWhichMeetCriteria().stream())
                .map(TvPackageResponseDto::getName)
                .toArray();
        Object[] expectedMainTvPackageNames = expected.getResultTvPackages().stream().
                map(resultTvPackageResponseDto -> resultTvPackageResponseDto.getFilteredTvPackage().getName())
                .toArray();

        List<String> actualOperatorsId = actual.getResultTvPackages().stream().map(ResultTvPackageResponseDto::getOperatorId).collect(Collectors.toList());
        List<String> actualOperatorsName = actual.getResultTvPackages().stream().map(ResultTvPackageResponseDto::getOperatorName).collect(Collectors.toList());

        Object[] actualExtraAvailableTvPackageNames = actual.getResultTvPackages().stream().
                flatMap(resultTvPackageResponseDto -> resultTvPackageResponseDto.getFilteredTvPackage().getExtraAvailableTvPackages().stream())
                .map(TvPackageResponseDto::getName).toArray();

        Object[] actualMeetCriteriaTvPackageNames = actual.getResultTvPackages().stream()
                .flatMap(resultTvPackageResponseDto -> resultTvPackageResponseDto.getFilteredTvPackage().getExtraTvPackagesWhichMeetCriteria().stream())
                .map(TvPackageResponseDto::getName).toArray();

        Object[] actualMainTvPackageNames = actual.getResultTvPackages().stream()
                .map(resultTvPackageResponseDto -> resultTvPackageResponseDto.getFilteredTvPackage().getName()).toArray();

        assertThat(actualOperatorsId)
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(expectedOperatorsId);
        assertThat(actualOperatorsName)
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(expectedOperatorsName);
        assertThat(actualMainTvPackageNames)
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(expectedMainTvPackageNames);
        assertThat(actualMeetCriteriaTvPackageNames)
                .containsExactlyInAnyOrder(expectedMeetCriteriaTvPackageNames);
        assertThat(actualExtraAvailableTvPackageNames)
                .containsExactlyInAnyOrder(expectedExtraAvailableTvPackageNames);

        // It is also option. But in this situation, error not display property place where was. For example if expected name of Tv Package will be different it not show difference in error stack.
//        assertThat(actual.getResultTvPackages())
//                .usingRecursiveComparison()
//                .ignoringAllOverriddenEquals()
//                .ignoringCollectionOrder()
//                .ignoringFields(
//                        "operatorImg",
//                        "operatorId",
//                        "operatorName",
//                        "filteredTvPackage.description",
//                        "filteredTvPackage.price",
//                        "filteredTvPackage.type",
//                        "filteredTvPackage.imgSrc",
//                        "filteredTvPackage.link",
//                        "filteredTvPackage.term",
//                        "filteredTvPackage.channels",
//                        "filteredTvPackage.extraTvPackagesWhichMeetCriteria.description",
//                        "filteredTvPackage.extraTvPackagesWhichMeetCriteria.price",
//                        "filteredTvPackage.extraTvPackagesWhichMeetCriteria.type",
//                        "filteredTvPackage.extraTvPackagesWhichMeetCriteria.imgSrc",
//                        "filteredTvPackage.extraTvPackagesWhichMeetCriteria.link",
//                        "filteredTvPackage.extraTvPackagesWhichMeetCriteria.term",
//                        "filteredTvPackage.extraTvPackagesWhichMeetCriteria.channels",
//                        "filteredTvPackage.extraTvPackagesWhichMeetCriteria.channels.name",
//                        "filteredTvPackage.extraTvPackagesWhichMeetCriteria.channels.imgScr",
//                        "filteredTvPackage.extraAvailableTvPackages.description",
//                        "filteredTvPackage.extraAvailableTvPackages.price",
//                        "filteredTvPackage.extraAvailableTvPackages.type",
//                        "filteredTvPackage.extraAvailableTvPackages.imgSrc",
//                        "filteredTvPackage.extraAvailableTvPackages.link",
//                        "filteredTvPackage.extraAvailableTvPackages.term",
//                        "filteredTvPackage.extraAvailableTvPackages.channels",
//                        "filteredTvPackage.extraAvailableTvPackages.channels.name",
//                        "filteredTvPackage.extraAvailableTvPackages.channels.imgSrc"
//                )
//                .isEqualTo(expected.getResultTvPackages());
    }

    private static Stream<Arguments> dataProvider() {
        CriteriaRequestDto criteria = new CriteriaRequestDto(new ArrayList<>(), 400d, asList(createChannelDto(NETFLIX_4K), createChannelDto(HBO_GO), createChannelDto(FOX_PLAY)), "24");
        ResultTvPackagesResponseDto expected = new ResultTvPackagesResponseDto(asList(
                createResultTvPackageDto(CANAL_PLUS_ID, CANAL_PLUS, new FilteredTvPackageResponseDto(
                        createTvPackageDto(COMFORT_CANAL_PLUS_NETFLIX),
                        asList(
                                createTvPackageDto(NETFLIX_4K_PACKAGE),
                                createTvPackageDto(FOX_PLAY),
                                createTvPackageDto(HBO_PLUS_HBO_GO)
                        ),
                        asList(
                                createTvPackageDto(ELEVEN_SPORTS),
                                createTvPackageDto(CANAL_PLUS_4K_ULTRA_HD),
                                createTvPackageDto(MULTI_MAN_PACK),
                                createTvPackageDto(PARAMOUNT_PLAY),
                                createTvPackageDto(HISTORY_PACKAGE),
                                createTvPackageDto(POLSAT_SPORT_PREMIUM),
                                createTvPackageDto(NATIONAL_GEOGRAPHIC_PLAY)
                        )
                )),
                createResultTvPackageDto(CANAL_PLUS_ID, CANAL_PLUS, new FilteredTvPackageResponseDto(
                        createTvPackageDto(EXTRA_CANAL_PLUS_NETFLIX),
                        asList(
                                createTvPackageDto(NETFLIX_4K_PACKAGE),
                                createTvPackageDto(FOX_PLAY),
                                createTvPackageDto(HBO_PLUS_HBO_GO)
                        ),
                        asList(
                                createTvPackageDto(ELEVEN_SPORTS),
                                createTvPackageDto(MULTI_MAN_PACK),
                                createTvPackageDto(NATIONAL_GEOGRAPHIC_PLAY),
                                createTvPackageDto(PARAMOUNT_PLAY),
                                createTvPackageDto(HISTORY_PACKAGE),
                                createTvPackageDto(CANAL_PLUS_4K_ULTRA_HD),
                                createTvPackageDto(POLSAT_SPORT_PREMIUM)
                        )
                ))
        ));

        return Stream.of(
                Arguments.of(criteria, expected)
        );
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
