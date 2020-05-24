package com.example.masterdegree.models.model.filter;

import com.example.masterdegree.models.model.Channel;
import com.example.masterdegree.models.model.TvPackage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static com.example.masterdegree.Constants.*;
import static com.example.masterdegree.DataCreationUtils.createTvPackage;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class FilteredTvPackageTest {

    private static Stream<Arguments> meetCriteriaExtraTvPackages() {
        FilteredTvPackage actualNotMeetCriteriaExtraTvPackages = new FilteredTvPackage(
                createTvPackage(COMFORT),
                new LinkedList<>(),
                new LinkedList<>()
        );
        FilteredTvPackage actualMeetCriteriaExtraTvPackages = new FilteredTvPackage(
                createTvPackage(COMFORT),
                asList(createTvPackage(HBO), createTvPackage(ELEVEN_SPORTS)),
                new LinkedList<>()
        );
        List<TvPackage> packages = asList(createTvPackage(COMFORT), createTvPackage(EXTRA));
        List<TvPackage> emptyPackages = new LinkedList<>();

        FilteredTvPackage expected = new FilteredTvPackage(
                createTvPackage(COMFORT),
                packages,
                new LinkedList<>()
        );
        FilteredTvPackage expectedEmptyPackages = new FilteredTvPackage(
                createTvPackage(COMFORT),
                emptyPackages,
                new LinkedList<>()
        );
        return Stream.of(
                Arguments.of(actualNotMeetCriteriaExtraTvPackages, packages, expected),
                Arguments.of(actualNotMeetCriteriaExtraTvPackages, emptyPackages, expectedEmptyPackages),
                Arguments.of(actualMeetCriteriaExtraTvPackages, packages, expected),
                Arguments.of(actualMeetCriteriaExtraTvPackages, emptyPackages, expectedEmptyPackages)
        );
    }

    private static Stream<Arguments> extraTvPackages() {
        FilteredTvPackage actualWithExtraTvPackages = new FilteredTvPackage(
                createTvPackage(COMFORT),
                new LinkedList<>(),
                new LinkedList<>(asList(createTvPackage(ELEVEN_SPORTS), createTvPackage(FOX_PLAY), createTvPackage(HBO)))
        );
        FilteredTvPackage actualWithoutExtraTvPackages = new FilteredTvPackage(
                createTvPackage(COMFORT),
                new LinkedList<>(),
                new LinkedList<>()
        );
        List<TvPackage> packages = asList(createTvPackage(HBO), createTvPackage(ELEVEN_SPORTS));
        List<TvPackage> emptyPackages = new LinkedList<>();

        FilteredTvPackage expectedWithExtraTvPackages = new FilteredTvPackage(
                createTvPackage(COMFORT),
                new LinkedList<>(),
                Collections.singletonList(createTvPackage(FOX_PLAY))
        );
        FilteredTvPackage expectedWithoutExtraTvPackages = new FilteredTvPackage(
                createTvPackage(COMFORT),
                new LinkedList<>(),
                new LinkedList<>()
        );
        return Stream.of(
                Arguments.of(actualWithExtraTvPackages, packages, expectedWithExtraTvPackages),
                Arguments.of(actualWithoutExtraTvPackages, packages, expectedWithoutExtraTvPackages),
                Arguments.of(actualWithExtraTvPackages, emptyPackages, actualWithExtraTvPackages),
                Arguments.of(actualWithoutExtraTvPackages, emptyPackages, actualWithoutExtraTvPackages)
        );
    }

    private static Stream<Arguments> extraTvPackagesIfContainsAllChannels() {
        FilteredTvPackage actualContainsAll = new FilteredTvPackage(
                createTvPackage(COMFORT),
                new LinkedList<>(),
                new LinkedList<>(asList(
                        createTvPackage(ELEVEN_SPORTS, asList(Channel.channel(ELEVEN_SPORTS_1), Channel.channel(BBC))),
                        createTvPackage(HBO, asList(Channel.channel(ELEVEN_SPORTS_1), Channel.channel(BBC)))
                ))
        );
        FilteredTvPackage actualNotContainsAll = new FilteredTvPackage(
                createTvPackage(COMFORT),
                new LinkedList<>(),
                new LinkedList<>(asList(
                        createTvPackage(ELEVEN_SPORTS, asList(Channel.channel(POLSAT_SPORT_PREMIUM), Channel.channel(ELEVEN_SPORTS_1))),
                        createTvPackage(HBO, Collections.singletonList(Channel.channel(BBC)))
                ))
        );
        FilteredTvPackage actualEmptyExtraTvPackages = new FilteredTvPackage(
                createTvPackage(COMFORT),
                new LinkedList<>(),
                new LinkedList<>()
        );
        List<TvPackage> tvPackages = asList(
                createTvPackage(FOX_PLAY, asList(Channel.channel(ELEVEN_SPORTS_1), Channel.channel(BBC), Channel.channel(ELEVEN_SPORTS_2))),
                createTvPackage(MULTI_MAN_PACK, asList(Channel.channel(ELEVEN_SPORTS_1), Channel.channel(BBC)))
        );
        FilteredTvPackage expectedContainsAll = new FilteredTvPackage(
                createTvPackage(COMFORT),
                new LinkedList<>(),
                new LinkedList<>()
        );
        FilteredTvPackage expectedEmpty = new FilteredTvPackage(
                createTvPackage(COMFORT),
                new LinkedList<>(),
                new LinkedList<>()
        );
        return Stream.of(
                Arguments.of(actualContainsAll, tvPackages, expectedContainsAll),
                Arguments.of(actualNotContainsAll, tvPackages, actualNotContainsAll),
                Arguments.of(actualEmptyExtraTvPackages, tvPackages, actualEmptyExtraTvPackages)
        );
    }

    @ParameterizedTest
    @MethodSource("meetCriteriaExtraTvPackages")
    void shouldReplaceExtraTvPackagesWith(FilteredTvPackage actual, List<TvPackage> listToReplace, FilteredTvPackage expected) {
        actual.replaceExtraTvPackagesWhichMeetCriteriaWith(listToReplace);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("extraTvPackages")
    void shouldRemoveGivenTvPackagesFromExtraTvPackages(FilteredTvPackage actual, List<TvPackage> listToRemove, FilteredTvPackage expected) {
        actual.removeGivenTvPackagesFromExtraTvPackages(listToRemove);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("extraTvPackagesIfContainsAllChannels")
    void shouldRemoveGivenTvPackageFromExtraTvPackagesIfContainsAllChannels(FilteredTvPackage actual, List<TvPackage> tvPackages, FilteredTvPackage expected) {
        actual.removeGivenTvPackageFromExtraTvPackagesIfContainsAllChannels(tvPackages);

        assertThat(actual).isEqualTo(expected);
    }
}