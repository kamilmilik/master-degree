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
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.masterdegree.Constants.*;
import static com.example.masterdegree.core.strategyfilters.DataCreationUtils.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;


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

        List<String> actualOperatorsId = actual.getResultTvPackages().stream().map(ResultTvPackageResponseDto::getOperatorId).collect(Collectors.toList());
        List<String> actualOperatorsName = actual.getResultTvPackages().stream().map(ResultTvPackageResponseDto::getOperatorName).collect(Collectors.toList());
        Object[] actualExtraAvailableTvPackageNames = prepareDataToCompareForAvailableTvPackagesName(actual);
        Object[] actualMeetCriteriaTvPackageNames = prepareDataToCompareForMeetCriteriaTvPackagesName(actual);
        Object[] actualMainTvPackageNames = prepareDataToCompareForMainTvPackagesName(actual);

        List<String> expectedOperatorsId = expected.getResultTvPackages().stream().map(ResultTvPackageResponseDto::getOperatorId).collect(Collectors.toList());
        List<String> expectedOperatorsName = expected.getResultTvPackages().stream().map(ResultTvPackageResponseDto::getOperatorName).collect(Collectors.toList());
        Object[] expectedExtraAvailableTvPackageNames = prepareDataToCompareForAvailableTvPackagesName(expected);
        Object[] expectedMeetCriteriaTvPackageNames = prepareDataToCompareForMeetCriteriaTvPackagesName(expected);
        Object[] expectedMainTvPackageNames = prepareDataToCompareForMainTvPackagesName(expected);
        assertThat(actualOperatorsId).as("Operator id")
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(expectedOperatorsId);
        assertThat(actualOperatorsName).as("Operator name")
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(expectedOperatorsName);
        assertThat(actualMainTvPackageNames).as("Main")
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(expectedMainTvPackageNames);
        assertThat(actualMeetCriteriaTvPackageNames).as("Meet criteria")
                .containsExactlyInAnyOrder(expectedMeetCriteriaTvPackageNames);
        assertThat(actualExtraAvailableTvPackageNames).as("Extra available")
                .containsExactlyInAnyOrder(expectedExtraAvailableTvPackageNames);
    }

    private static Stream<Arguments> dataProvider() {
        CriteriaRequestDto criteriaChannelsDefaultPriceAndTerm = new CriteriaRequestDto(new ArrayList<>(), 400d, asList(NETFLIX_4K, HBO_GO, FOX_PLAY), "24");
        ResultTvPackagesResponseDto expectedComfortAndExtra = new ResultTvPackagesResponseDto(asList(
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
        CriteriaRequestDto criteriaOperatorPriceChannelsDefaultTerm = new CriteriaRequestDto(
                new ArrayList<>(Collections.singletonList("5e77b71972e086126cc4e6b3")),
                143d,
                asList(DTX_HD, SPORT_PREMIUM_1, DORCEL_TV_HD),
                "24");
        ResultTvPackagesResponseDto expectedExtraAndSuperPremium = new ResultTvPackagesResponseDto(asList(
                createResultTvPackageDto(CANAL_PLUS_ID, CANAL_PLUS, new FilteredTvPackageResponseDto(
                        createTvPackageDto(EXTRA_CANAL_PLUS),
                        asList(
                                createTvPackageDto(POLSAT_SPORT_PREMIUM),
                                createTvPackageDto(MULTI_MAN_PACK)
                        ),
                        asList(
                                createTvPackageDto(HBO),
                                createTvPackageDto(HBO_PLUS_HBO_GO),
                                createTvPackageDto(ELEVEN_SPORTS),
                                createTvPackageDto(FOX_PLAY),
                                createTvPackageDto(NATIONAL_GEOGRAPHIC_PLAY),
                                createTvPackageDto(PARAMOUNT_PLAY),
                                createTvPackageDto(CANAL_PLUS_4K_ULTRA_HD)
                        )
                )),
                createResultTvPackageDto(CANAL_PLUS_ID, CANAL_PLUS, new FilteredTvPackageResponseDto(
                        createTvPackageDto(CANAL_PLUS_SUPERPREMIUM),
                        asList(
                                createTvPackageDto(POLSAT_SPORT_PREMIUM),
                                createTvPackageDto(DORCEL_TV)
                        ),
                        asList(
                                createTvPackageDto(MULTI_MAN_PACK),
                                createTvPackageDto(FOX_PLAY),
                                createTvPackageDto(NATIONAL_GEOGRAPHIC_PLAY),
                                createTvPackageDto(ADVENTURE),
                                createTvPackageDto(HISTORY_PACKAGE)
                        )
                ))
        ));
        CriteriaRequestDto criteriaChannelInMainTvPackage = new CriteriaRequestDto(
                new ArrayList<>(Collections.singletonList("5e77b71972e086126cc4e6b3")), 75d, Collections.singletonList(BOOMERANG_HD), "24"
        );
        ResultTvPackagesResponseDto expectedCriteriaChannelInMainTvPackage = new ResultTvPackagesResponseDto(asList(
                createResultTvPackageDto(CANAL_PLUS_ID, CANAL_PLUS, new FilteredTvPackageResponseDto(
                        createTvPackageDto(EXTRA),
                        new LinkedList<>(),
                        asList(
                                createTvPackageDto(HBO),
                                createTvPackageDto(HBO_PLUS_HBO_GO),
                                createTvPackageDto(ELEVEN_SPORTS),
                                createTvPackageDto(MULTI_MAN_PACK),
                                createTvPackageDto(FOX_PLAY),
                                createTvPackageDto(NATIONAL_GEOGRAPHIC_PLAY),
                                createTvPackageDto(HISTORY_PACKAGE)
                        )
                ))
        ));

        CriteriaRequestDto criteriaChannelInExtraTvPackage = new CriteriaRequestDto(
                new ArrayList<>(Collections.singletonList("5e77b71972e086126cc4e6b3")), 131d, Collections.singletonList(NETFLIX_4K), "24"
        );
        ResultTvPackagesResponseDto expectedCriteriaChannelInExtraTvPackage = new ResultTvPackagesResponseDto(asList(
                createResultTvPackageDto(CANAL_PLUS_ID, CANAL_PLUS, new FilteredTvPackageResponseDto(
                        createTvPackageDto(COMFORT_CANAL_PLUS_NETFLIX),
                        asList(createTvPackageDto(NETFLIX_4K_PACKAGE)),
                        asList(
                                createTvPackageDto(HBO),
                                createTvPackageDto(HBO_PLUS_HBO_GO),
                                createTvPackageDto(ELEVEN_SPORTS),
                                createTvPackageDto(CANAL_PLUS_4K_ULTRA_HD),
                                createTvPackageDto(MULTI_MAN_PACK),
                                createTvPackageDto(PARAMOUNT_PLAY),
                                createTvPackageDto(HISTORY_PACKAGE),
                                createTvPackageDto(POLSAT_SPORT_PREMIUM),
                                createTvPackageDto(FOX_PLAY),
                                createTvPackageDto(NATIONAL_GEOGRAPHIC_PLAY)
                        )
                ))
        ));
        return Stream.of(
                Arguments.of(criteriaChannelsDefaultPriceAndTerm, expectedComfortAndExtra),
                Arguments.of(criteriaOperatorPriceChannelsDefaultTerm, expectedExtraAndSuperPremium),
                Arguments.of(criteriaChannelInMainTvPackage, expectedCriteriaChannelInMainTvPackage),
                Arguments.of(criteriaChannelInExtraTvPackage, expectedCriteriaChannelInExtraTvPackage)
        );
    }

    private Object[] prepareDataToCompareForAvailableTvPackagesName(ResultTvPackagesResponseDto input) {
        return input.getResultTvPackages().stream()
                .flatMap(resultTvPackageResponseDto -> resultTvPackageResponseDto.getFilteredTvPackage().getExtraAvailableTvPackages().stream())
                .map(TvPackageResponseDto::getName).toArray();
    }

    private Object[] prepareDataToCompareForMeetCriteriaTvPackagesName(ResultTvPackagesResponseDto input) {
        return input.getResultTvPackages().stream()
                .flatMap(resultTvPackageResponseDto -> resultTvPackageResponseDto.getFilteredTvPackage().getExtraTvPackagesWhichMeetCriteria().stream())
                .map(TvPackageResponseDto::getName).toArray();
    }

    private Object[] prepareDataToCompareForMainTvPackagesName(ResultTvPackagesResponseDto input) {
        return input.getResultTvPackages().stream()
                .map(resultTvPackageResponseDto -> resultTvPackageResponseDto.getFilteredTvPackage().getName()).toArray();
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
