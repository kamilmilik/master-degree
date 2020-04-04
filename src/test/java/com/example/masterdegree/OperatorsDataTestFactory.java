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

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class OperatorsDataTestFactory extends Constants {


    private static List<Operator> operators;

    public static List<Operator> create() {
        List<Channel> startTvPackageChannels = asList(
                Channel.newChannel(FUN_TV),
                Channel.newChannel(ALE_KINO_HD),
                Channel.newChannel(AIRGANG_TV),
                Channel.newChannel(ATM_ROZRYWKA),
                Channel.newChannel(BBC)
        );
        List<Channel> hboExtraTvPackageChannels = asList(
                Channel.newChannel(HBO),
                Channel.newChannel(HBO2),
                Channel.newChannel(HBO3)
        );
        List<Channel> hboGoExtraTvPackageChannels = Stream.of(hboExtraTvPackageChannels, Collections.singletonList(
                Channel.newChannel(HBO_GO)
        )).flatMap(Collection::stream).collect(toList());
        List<Channel> elevenSportsExtraTvPackageChannels = asList(
                Channel.newChannel(ELEVEN_SPORTS),
                Channel.newChannel(ELEVEN_SPORTS1),
                Channel.newChannel(ELEVEN_SPORTS2)
        );
        TvPackage hboExtraTvPackage = TvPackageTestFactory.create(HBO, 25d, hboExtraTvPackageChannels);
        TvPackage hboGoExtraTvPackage = TvPackageTestFactory.create(HBO_GO, 35d, hboGoExtraTvPackageChannels);
        TvPackage elevenExtraTvPackage = TvPackageTestFactory.create(ELEVEN_SPORTS, 15d, elevenSportsExtraTvPackageChannels);
        TvPackage foxPlayExtraTvPackage = TvPackageTestFactory.create(FOX_PLAY, 5d, Collections.singletonList(Channel.newChannel(FOX_PLAY)));
        MainTvPackage startTvPackage = MainTvPackageTestFactory.create(
                CANAL_PLUS_START,
                18.11d,
                startTvPackageChannels,
                asList(hboExtraTvPackage, hboGoExtraTvPackage, elevenExtraTvPackage, foxPlayExtraTvPackage)
        );
        List<Channel> multiManPackChannels = asList(
                Channel.newChannel(DORCEL),
                Channel.newChannel(BLUR_HUSTLER),
                Channel.newChannel(HUSTLER_HD),
                Channel.newChannel(FIGHTBOX),
                Channel.newChannel(HISTORY2),
                Channel.newChannel(HISTORY),
                Channel.newChannel(ADVENTURE)
        );
        List<Channel> dorcelTvChannels = Collections.singletonList(Channel.newChannel(DORCEL));
        TvPackage multiManPackExtraTvPackage = TvPackageTestFactory.create(MULTI_MAN_PACK, 20d, multiManPackChannels);
        TvPackage dorcelExtraTvPackage = TvPackageTestFactory.create(DORCEL, 10d, dorcelTvChannels);
        List<Channel> superPremiumTvPackageChannels = Stream.of(startTvPackageChannels, asList(
                Channel.newChannel(FUN_DANCE),
                Channel.newChannel(FUN_GOLD_HTS),
                Channel.newChannel(ACTIVE_FAMILY),
                Channel.newChannel(AMC),
                Channel.newChannel(ANIMAL_PLANET_HD)
        )).flatMap(Collection::stream).collect(toList());

        MainTvPackage superPremiumTvPackage = MainTvPackageTestFactory.create(
                CANAL_PLUS_SUPERPREMIUM,
                126.86d,
                superPremiumTvPackageChannels,
                asList(dorcelExtraTvPackage, foxPlayExtraTvPackage, multiManPackExtraTvPackage, elevenExtraTvPackage)
        );
        Operator operatorCanalPlus = new Operator(
                CANAL_PLUS_ID,
                CANAL_PLUS, "img",
                asList(startTvPackage, superPremiumTvPackage)
        );
        List<Channel> smallFamilyChannels = asList(
                Channel.newChannel(ATM_ROZRYWKA),
                Channel.newChannel(POLSAT),
                Channel.newChannel(TVN),
                Channel.newChannel(HISTORY)
        );
        List<Channel> familyChannels = Stream.of(smallFamilyChannels, asList(
                Channel.newChannel(FUN_TV),
                Channel.newChannel(ALE_KINO_HD)
        )).flatMap(Collection::stream).collect(toList());
        List<Channel> funChannels = asList(
                Channel.newChannel(BRAZZERS),
                Channel.newChannel(HUSTLER_HD),
                Channel.newChannel(PRIVATE_HD),
                Channel.newChannel(VIVD_HD)
        );
        TvPackage funExtraTvPackage = TvPackageTestFactory.create(FUN, 10d, funChannels);
        List<Channel> polsatSportPremiumChannels = asList(
                Channel.newChannel(SPORT_PREMIUM1),
                Channel.newChannel(SPORT_PREMIUM2)
        );
        TvPackage polsatSportPremiumExtraTvPackage = TvPackageTestFactory.create(POLSAT_SPORT_PREMIUM, 20d, polsatSportPremiumChannels);
        MainTvPackage smallFamilyTvPackage = MainTvPackageTestFactory.create(
                SMALL_FAMILY_CYFROWY_POLSAT,
                12d,
                smallFamilyChannels,
                asList(hboExtraTvPackage, elevenExtraTvPackage)
        );
        MainTvPackage familyTvPackage = MainTvPackageTestFactory.create(
                FAMILY_CYFROWY_POLSAT,
                52d,
                familyChannels,
                asList(hboExtraTvPackage, elevenExtraTvPackage, funExtraTvPackage, polsatSportPremiumExtraTvPackage)
        );
        Operator operatorCyfrowyPolsat = new Operator(
                CYFROWY_POLSAT_ID,
                CYFROWY_POLSAT, "img",
                asList(smallFamilyTvPackage, familyTvPackage)
        );
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
