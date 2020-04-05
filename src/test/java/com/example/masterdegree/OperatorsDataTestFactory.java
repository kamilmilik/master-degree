package com.example.masterdegree;

import com.example.masterdegree.models.dto.ResultTvPackagesResponseDto;
import com.example.masterdegree.models.mappers.ResultTvPackagesMapper;
import com.example.masterdegree.models.model.Channel;
import com.example.masterdegree.models.model.MainTvPackage;
import com.example.masterdegree.models.model.Operator;
import com.example.masterdegree.models.model.TvPackage;
import com.example.masterdegree.models.model.filter.FilteredTvPackage;
import com.example.masterdegree.models.model.filter.ResultTvPackage;
import com.example.masterdegree.models.model.filter.ResultTvPackages;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class OperatorsDataTestFactory extends Constants {


    private static List<Operator> operators;

    public static List<Channel> channels(String[] channelsName) {
        List<Channel> channels = new ArrayList<>();
        for (String channel : channelsName) {
            channels.add(Channel.newChannel(channel));
        }
        return channels;
    }

    public static List<Channel> channels(List<Channel> channelsBasic, String[] channelsName) {
        List<Channel> channels = new ArrayList<>(channelsBasic);
        for (String channel : channelsName) {
            channels.add(Channel.newChannel(channel));
        }
        return channels;
    }

    public static List<Operator> create() {
        List<Channel> startTvPackageChannels = channels(new String[]{FUN_TV, ALE_KINO_HD, AIRGANG_TV, ATM_ROZRYWKA, BBC});
        List<Channel> hboExtraTvPackageChannels = channels(new String[]{HBO, HBO2, HBO3});
        List<Channel> hboGoExtraTvPackageChannels = channels(hboExtraTvPackageChannels, new String[]{HBO_GO});
        List<Channel> elevenSportsExtraTvPackageChannels = channels(new String[]{ELEVEN_SPORTS, ELEVEN_SPORTS1, ELEVEN_SPORTS2});
        List<Channel> foxPlayExtraTvPackageChannels = channels(new String[]{FOX_PLAY});
        List<Channel> smallFamilyChannels = channels(new String[]{ATM_ROZRYWKA, POLSAT, TVN, HISTORY});
        List<Channel> familyChannels = channels(new String[]{FUN_TV, ALE_KINO_HD});
        List<Channel> funChannels = channels(new String[]{BRAZZERS, HUSTLER_HD, PRIVATE_HD, VIVD_HD});
        List<Channel> polsatSportPremiumChannels = channels(new String[]{SPORT_PREMIUM1, SPORT_PREMIUM2});
        List<Channel> multiManPackChannels = channels(new String[]{DORCEL, BLUR_HUSTLER, HUSTLER_HD, FIGHTBOX, HISTORY2, HISTORY, ADVENTURE});
        List<Channel> dorcelTvChannels = channels(new String[]{DORCEL});
        List<Channel> superPremiumTvPackageChannels = channels(startTvPackageChannels, new String[]{FUN_DANCE, FUN_GOLD_HTS, ACTIVE_FAMILY, AMC, ANIMAL_PLANET_HD});

        TvPackage hboExtraTvPackage = TvPackageTestFactory.create(HBO, 25d, hboExtraTvPackageChannels);
        TvPackage hboGoExtraTvPackage = TvPackageTestFactory.create(HBO_GO, 35d, hboGoExtraTvPackageChannels);
        TvPackage elevenExtraTvPackage = TvPackageTestFactory.create(ELEVEN_SPORTS, 15d, elevenSportsExtraTvPackageChannels);
        TvPackage foxPlayExtraTvPackage = TvPackageTestFactory.create(FOX_PLAY, 5d, foxPlayExtraTvPackageChannels);
        TvPackage multiManPackExtraTvPackage = TvPackageTestFactory.create(MULTI_MAN_PACK, 20d, multiManPackChannels);
        TvPackage dorcelExtraTvPackage = TvPackageTestFactory.create(DORCEL, 10d, dorcelTvChannels);
        TvPackage funExtraTvPackage = TvPackageTestFactory.create(FUN, 10d, funChannels);
        TvPackage polsatSportPremiumExtraTvPackage = TvPackageTestFactory.create(POLSAT_SPORT_PREMIUM, 20d, polsatSportPremiumChannels);

        Operator operatorCanalPlus = new Operator(CANAL_PLUS_ID, CANAL_PLUS, "img", asList(
                MainTvPackageTestFactory.create(CANAL_PLUS_START, 18.11d, startTvPackageChannels, asList(
                        hboExtraTvPackage, hboGoExtraTvPackage, elevenExtraTvPackage, foxPlayExtraTvPackage
                )),
                MainTvPackageTestFactory.create(CANAL_PLUS_SUPERPREMIUM, 126.86d, superPremiumTvPackageChannels, asList(
                        dorcelExtraTvPackage, foxPlayExtraTvPackage, multiManPackExtraTvPackage, elevenExtraTvPackage
                ))
        ));

        Operator operatorCyfrowyPolsat = new Operator(CYFROWY_POLSAT_ID, CYFROWY_POLSAT, "img", asList(
                MainTvPackageTestFactory.create(SMALL_FAMILY_CYFROWY_POLSAT, 12d, smallFamilyChannels, asList(
                        hboExtraTvPackage, elevenExtraTvPackage
                )),
                MainTvPackageTestFactory.create(FAMILY_CYFROWY_POLSAT, 52d, familyChannels, asList(
                        hboExtraTvPackage, elevenExtraTvPackage, funExtraTvPackage, polsatSportPremiumExtraTvPackage
                ))
        ));

        operators = asList(operatorCanalPlus, operatorCyfrowyPolsat);
        return operators;
    }

    public static Operator findOperatorById(String id) {
        return operators.stream().filter(operator -> operator.getId().equals(id)).findFirst().orElse(null);
    }

    public static MainTvPackage findFromOperatorMainTvPackageByName(Operator operator, String name) {
        return operator.getTvPackages().stream().filter(mainTvPackage -> mainTvPackage.getName().equals(name)).findFirst().orElse(null);
    }

    public static TvPackage findExtraTvPackageFromMainTvPackageByName(MainTvPackage mainTvPackage, String name) {
        return mainTvPackage.getExtraTvPackages().stream().filter(tvPackage -> tvPackage.getName().equals(name)).findFirst().orElse(null);
    }

    public static class TvPackageTestFactory {
        public static TvPackage create(String name, Double price, List<Channel> channels) {
            return new TvPackage(name, "", price, "", "", "", "", "", channels);
        }
    }

    public static class MainTvPackageTestFactory {
        public static MainTvPackage create(String name, Double price, List<Channel> channels, List<TvPackage> extraTvPackages) {
            return new MainTvPackage(name, "", price, "", "", "", "", "", channels, extraTvPackages);
        }
    }


    public static class ExpectedResultDataTestFactory {
        public static class Expected {
            String expectedOperatorId;
            List<ExpectedMainTvPackage> expectedMainTvPackages;

            public Expected(String expectedOperatorId, List<ExpectedMainTvPackage> expectedMainTvPackages) {
                this.expectedOperatorId = expectedOperatorId;
                this.expectedMainTvPackages = expectedMainTvPackages;
            }
        }

        public static class ExpectedMainTvPackage {
            String expectedMainTvPackage;
            String[] expectedExtraTvPackages;

            public ExpectedMainTvPackage(String expectedMainTvPackage, String[] expectedExtraTvPackages) {
                this.expectedMainTvPackage = expectedMainTvPackage;
                this.expectedExtraTvPackages = expectedExtraTvPackages;
            }
        }

        public static Expected createExpected(String expectedOperatorId, Map<String, String[]> expectedMainTvPackageWithExtra) {
            List<ExpectedMainTvPackage> expectedMainTvPackages = new ArrayList<>();
            expectedMainTvPackageWithExtra.forEach((expectedMainTvPackage, expectedExtraTvPackages) -> {
                expectedMainTvPackages.add(new ExpectedMainTvPackage(expectedMainTvPackage, expectedExtraTvPackages));
            });
            return new Expected(expectedOperatorId, expectedMainTvPackages);
        }

        public static ResultTvPackagesResponseDto getExpectedResult(List<Expected> expectedOperators) {
            List<ResultTvPackage> resultTvPackages = new ArrayList<>();
            for (Expected operator : expectedOperators) {
                Operator expectedOperator = OperatorsDataTestFactory.findOperatorById(operator.expectedOperatorId);
                for (ExpectedMainTvPackage expectedMainTvPackage : operator.expectedMainTvPackages) {
                    MainTvPackage mainTvPackage = OperatorsDataTestFactory.findFromOperatorMainTvPackageByName(expectedOperator, expectedMainTvPackage.expectedMainTvPackage);
                    List<TvPackage> expectedExtra = new ArrayList<>();
                    for (String expectedExtraTvPackage : expectedMainTvPackage.expectedExtraTvPackages) {
                        expectedExtra.add(OperatorsDataTestFactory.findExtraTvPackageFromMainTvPackageByName(mainTvPackage, expectedExtraTvPackage));
                    }
                    resultTvPackages.add(resultTvPackage(expectedOperator, mainTvPackage, expectedExtra));
                }
            }
            ResultTvPackages expectedResultTvPackages = new ResultTvPackages(resultTvPackages);
            ResultTvPackagesMapper resultTvPackagesMapper = new ResultTvPackagesMapper(new ModelMapper());
            return resultTvPackagesMapper.convertToDto(expectedResultTvPackages);
        }

        public static ResultTvPackagesResponseDto expectedResultChannelEleven() {
            Operator cyfrowyPolsat = OperatorsDataTestFactory.findOperatorById(CYFROWY_POLSAT_ID);
            Operator canalPlus = OperatorsDataTestFactory.findOperatorById(CANAL_PLUS_ID);
            MainTvPackage startMainTvPackage = OperatorsDataTestFactory.findFromOperatorMainTvPackageByName(canalPlus, CANAL_PLUS_START);
            List<TvPackage> startExtraTvPackagesWhichMeetCriteria = Collections.singletonList(
                    OperatorsDataTestFactory.findExtraTvPackageFromMainTvPackageByName(startMainTvPackage, ELEVEN_SPORTS)
            );
            MainTvPackage superPremiumTvPackage = OperatorsDataTestFactory.findFromOperatorMainTvPackageByName(canalPlus, CANAL_PLUS_SUPERPREMIUM);
            List<TvPackage> superPremiumExtraTvPackagesWhichMeetCriteria = Collections.singletonList(
                    OperatorsDataTestFactory.findExtraTvPackageFromMainTvPackageByName(superPremiumTvPackage, ELEVEN_SPORTS)
            );
            MainTvPackage smallFamilyTvPackage = OperatorsDataTestFactory.findFromOperatorMainTvPackageByName(cyfrowyPolsat, SMALL_FAMILY_CYFROWY_POLSAT);
            List<TvPackage> smallFamilyTvPackagesWhichMeetCriteria = Collections.singletonList(
                    OperatorsDataTestFactory.findExtraTvPackageFromMainTvPackageByName(smallFamilyTvPackage, ELEVEN_SPORTS)
            );
            MainTvPackage familyTvPackage = OperatorsDataTestFactory.findFromOperatorMainTvPackageByName(cyfrowyPolsat, FAMILY_CYFROWY_POLSAT);
            List<TvPackage> familyTvPackagesWhichMeetCriteria = Collections.singletonList(
                    OperatorsDataTestFactory.findExtraTvPackageFromMainTvPackageByName(familyTvPackage, ELEVEN_SPORTS)
            );
            ResultTvPackage startResult = resultTvPackage(canalPlus, startMainTvPackage, startExtraTvPackagesWhichMeetCriteria);
            ResultTvPackage superPremiumResult = resultTvPackage(canalPlus, superPremiumTvPackage, superPremiumExtraTvPackagesWhichMeetCriteria);
            ResultTvPackage smallFamilyResult = resultTvPackage(cyfrowyPolsat, smallFamilyTvPackage, smallFamilyTvPackagesWhichMeetCriteria);
            ResultTvPackage familyResult = resultTvPackage(cyfrowyPolsat, familyTvPackage, familyTvPackagesWhichMeetCriteria);
            ResultTvPackages expectedResultTvPackages = new ResultTvPackages(asList(
                    startResult,
                    superPremiumResult,
                    smallFamilyResult,
                    familyResult
            ));

            ResultTvPackagesMapper resultTvPackagesMapper = new ResultTvPackagesMapper(new ModelMapper());
            return resultTvPackagesMapper.convertToDto(expectedResultTvPackages);
        }

        private static ResultTvPackage resultTvPackage(Operator operator, MainTvPackage mainTvPackage, List<TvPackage> meetCriteria) {
            return new ResultTvPackage(
                    operator.getId(),
                    operator.getName(),
                    operator.getImgSrc(),
                    new FilteredTvPackage(
                            mainTvPackage,
                            meetCriteria,
                            mainTvPackage.getExtraTvPackages()
                    )
            );
        }
    }

}
