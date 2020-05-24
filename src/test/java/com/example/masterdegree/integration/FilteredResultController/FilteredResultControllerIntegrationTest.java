package com.example.masterdegree.integration.FilteredResultController;

import com.example.masterdegree.MasterDegreeApplication;
import com.example.masterdegree.controllers.FilteredResultController;
import com.example.masterdegree.models.dto.CriteriaRequestDto;
import com.example.masterdegree.models.model.filter.FilteredTvPackage;
import com.example.masterdegree.models.model.filter.ResultTvPackages;
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
import java.util.stream.Stream;

import static com.example.masterdegree.Constants.*;
import static com.example.masterdegree.DataCreationUtils.createResultTv;
import static com.example.masterdegree.DataCreationUtils.createTvPackage;
import static java.util.Arrays.asList;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {MasterDegreeApplication.class, FilteredResultController.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FilteredResultControllerIntegrationTest {

    private static final String URI = "/api/result";
    private final TestRestTemplate restTemplate = new TestRestTemplate();
    private final HttpHeaders headers = new HttpHeaders();
    @LocalServerPort
    private int port;
    @Autowired
    private ObjectMapper objectMapper;

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void shouldReturnFilteredResult(CriteriaRequestDto criteria, ResultTvPackages expected) throws JsonProcessingException {
        HttpEntity<CriteriaRequestDto> entity = new HttpEntity<>(criteria, headers);
        ResponseEntity<String> response = this.restTemplate
                .postForEntity(createURLWithPort(URI), entity, String.class);

        ResultTvPackages actual = objectMapper.readValue(response.getBody(), ResultTvPackages.class);

        FilteredResultControllerAssertion.assertThat(actual)
                .hasOperatorsId(expected)
                .hasOperatorsName(expected)
                .hasMainTvPackageNames(expected)
                .hasExtraAvailableTvPackageNames(expected)
                .hasMeetCriteriaTvPackageNames(expected);
    }

    private static Stream<Arguments> dataProvider() {
        CriteriaRequestDto criteriaChannelsDefaultPriceAndTerm = new CriteriaRequestDto(new ArrayList<>(), 400d, asList(NETFLIX_4K, HBO_GO, FOX_PLAY), TERM_24);
        ResultTvPackages expectedComfortAndExtra = new ResultTvPackages(asList(
                createResultTv(CANAL_PLUS_ID, CANAL_PLUS, new FilteredTvPackage(
                        createTvPackage(COMFORT_CANAL_PLUS_NETFLIX),
                        asList(
                                createTvPackage(NETFLIX_4K_PACKAGE),
                                createTvPackage(FOX_PLAY),
                                createTvPackage(HBO_PLUS_HBO_GO)
                        ),
                        asList(
                                createTvPackage(ELEVEN_SPORTS),
                                createTvPackage(CANAL_PLUS_4K_ULTRA_HD),
                                createTvPackage(MULTI_MAN_PACK),
                                createTvPackage(PARAMOUNT_PLAY),
                                createTvPackage(HISTORY_PACKAGE),
                                createTvPackage(POLSAT_SPORT_PREMIUM),
                                createTvPackage(NATIONAL_GEOGRAPHIC_PLAY)
                        )
                )),
                createResultTv(CANAL_PLUS_ID, CANAL_PLUS, new FilteredTvPackage(
                        createTvPackage(EXTRA_CANAL_PLUS_NETFLIX),
                        asList(
                                createTvPackage(NETFLIX_4K_PACKAGE),
                                createTvPackage(FOX_PLAY),
                                createTvPackage(HBO_PLUS_HBO_GO)
                        ),
                        asList(
                                createTvPackage(ELEVEN_SPORTS),
                                createTvPackage(MULTI_MAN_PACK),
                                createTvPackage(NATIONAL_GEOGRAPHIC_PLAY),
                                createTvPackage(PARAMOUNT_PLAY),
                                createTvPackage(HISTORY_PACKAGE),
                                createTvPackage(CANAL_PLUS_4K_ULTRA_HD),
                                createTvPackage(POLSAT_SPORT_PREMIUM)
                        )
                ))
        ));
        CriteriaRequestDto criteriaOperatorPriceChannelsDefaultTerm = new CriteriaRequestDto(
                new ArrayList<>(Collections.singletonList("5e77b71972e086126cc4e6b3")),
                143d,
                asList(DTX_HD, SPORT_PREMIUM_1, DORCEL_TV_HD),
                TERM_24);
        ResultTvPackages expectedExtraAndSuperPremium = new ResultTvPackages(asList(
                createResultTv(CANAL_PLUS_ID, CANAL_PLUS, new FilteredTvPackage(
                        createTvPackage(EXTRA_CANAL_PLUS),
                        asList(
                                createTvPackage(POLSAT_SPORT_PREMIUM),
                                createTvPackage(MULTI_MAN_PACK)
                        ),
                        asList(
                                createTvPackage(HBO),
                                createTvPackage(HBO_PLUS_HBO_GO),
                                createTvPackage(ELEVEN_SPORTS),
                                createTvPackage(FOX_PLAY),
                                createTvPackage(NATIONAL_GEOGRAPHIC_PLAY),
                                createTvPackage(PARAMOUNT_PLAY),
                                createTvPackage(CANAL_PLUS_4K_ULTRA_HD)
                        )
                )),
                createResultTv(CANAL_PLUS_ID, CANAL_PLUS, new FilteredTvPackage(
                        createTvPackage(CANAL_PLUS_SUPERPREMIUM),
                        asList(
                                createTvPackage(POLSAT_SPORT_PREMIUM),
                                createTvPackage(DORCEL_TV)
                        ),
                        asList(
                                createTvPackage(MULTI_MAN_PACK),
                                createTvPackage(FOX_PLAY),
                                createTvPackage(NATIONAL_GEOGRAPHIC_PLAY),
                                createTvPackage(ADVENTURE),
                                createTvPackage(HISTORY_PACKAGE)
                        )
                ))
        ));
        CriteriaRequestDto criteriaChannelInMainTvPackage = new CriteriaRequestDto(
                new ArrayList<>(Collections.singletonList("5e77b71972e086126cc4e6b3")), 75d, Collections.singletonList(BOOMERANG_HD), TERM_24
        );
        ResultTvPackages expectedCriteriaChannelInMainTvPackage = new ResultTvPackages(Collections.singletonList(
                createResultTv(CANAL_PLUS_ID, CANAL_PLUS, new FilteredTvPackage(
                        createTvPackage(EXTRA),
                        new LinkedList<>(),
                        asList(
                                createTvPackage(HBO),
                                createTvPackage(HBO_PLUS_HBO_GO),
                                createTvPackage(ELEVEN_SPORTS),
                                createTvPackage(MULTI_MAN_PACK),
                                createTvPackage(FOX_PLAY),
                                createTvPackage(NATIONAL_GEOGRAPHIC_PLAY),
                                createTvPackage(HISTORY_PACKAGE)
                        )
                ))
        ));

        CriteriaRequestDto criteriaChannelInExtraTvPackage = new CriteriaRequestDto(
                new ArrayList<>(Collections.singletonList("5e77b71972e086126cc4e6b3")), 131d, Collections.singletonList(NETFLIX_4K), TERM_24
        );
        ResultTvPackages expectedCriteriaChannelInExtraTvPackage = new ResultTvPackages(Collections.singletonList(
                createResultTv(CANAL_PLUS_ID, CANAL_PLUS, new FilteredTvPackage(
                        createTvPackage(COMFORT_CANAL_PLUS_NETFLIX),
                        Collections.singletonList(createTvPackage(NETFLIX_4K_PACKAGE)),
                        asList(
                                createTvPackage(HBO),
                                createTvPackage(HBO_PLUS_HBO_GO),
                                createTvPackage(ELEVEN_SPORTS),
                                createTvPackage(CANAL_PLUS_4K_ULTRA_HD),
                                createTvPackage(MULTI_MAN_PACK),
                                createTvPackage(PARAMOUNT_PLAY),
                                createTvPackage(HISTORY_PACKAGE),
                                createTvPackage(POLSAT_SPORT_PREMIUM),
                                createTvPackage(FOX_PLAY),
                                createTvPackage(NATIONAL_GEOGRAPHIC_PLAY)
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

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
