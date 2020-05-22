package com.example.masterdegree.integration.FilteredResultController;

import com.example.masterdegree.models.model.TvPackage;
import com.example.masterdegree.models.model.filter.ResultTvPackage;
import com.example.masterdegree.models.model.filter.ResultTvPackages;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.stream.Collectors;

public class FilteredResultControllerAssertion {

    private final ResultTvPackages actual;

    private FilteredResultControllerAssertion(ResultTvPackages actual) {
        this.actual = actual;
    }

    public static FilteredResultControllerAssertion assertThat(ResultTvPackages actual) {
        return new FilteredResultControllerAssertion(actual);
    }

    FilteredResultControllerAssertion hasOperatorsId(ResultTvPackages expected) {
        List<String> actualOperatorsId = operatorsId(actual);

        List<String> expectedOperatorsId = operatorsId(expected);

        Assertions.assertThat(actualOperatorsId).as("Operator id")
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(expectedOperatorsId);

        return this;
    }

    FilteredResultControllerAssertion hasOperatorsName(ResultTvPackages expected) {
        List<String> actualOperatorsName = operatorsName(actual);

        List<String> expectedOperatorsName = operatorsName(expected);

        Assertions.assertThat(actualOperatorsName).as("Operator name")
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(expectedOperatorsName);

        return this;
    }

    FilteredResultControllerAssertion hasMainTvPackageNames(ResultTvPackages expected) {
        Object[] actualMainTvPackageNames = mainTvPackagesName(actual);

        Object[] expectedMainTvPackageNames = mainTvPackagesName(expected);

        Assertions.assertThat(actualMainTvPackageNames).as("Main")
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(expectedMainTvPackageNames);

        return this;
    }

    FilteredResultControllerAssertion hasMeetCriteriaTvPackageNames(ResultTvPackages expected) {
        Object[] actualMeetCriteriaTvPackageNames = meetCriteriaTvPackageNames(actual);

        Object[] expectedMeetCriteriaTvPackageNames = meetCriteriaTvPackageNames(expected);

        Assertions.assertThat(actualMeetCriteriaTvPackageNames).as("Meet criteria")
                .containsExactlyInAnyOrder(expectedMeetCriteriaTvPackageNames);

        return this;
    }

    FilteredResultControllerAssertion hasExtraAvailableTvPackageNames(ResultTvPackages expected) {
        Object[] actualExtraAvailableTvPackageNames = availableTvPackagesName(actual);

        Object[] expectedExtraAvailableTvPackageNames = availableTvPackagesName(expected);

        Assertions.assertThat(actualExtraAvailableTvPackageNames).as("Extra available")
                .containsExactlyInAnyOrder(expectedExtraAvailableTvPackageNames);

        return this;
    }

    private List<String> operatorsId(ResultTvPackages input) {
        return input.getResultTvPackages().stream()
                .map(ResultTvPackage::getOperatorId)
                .collect(Collectors.toList());
    }

    private List<String> operatorsName(ResultTvPackages input) {
        return input.getResultTvPackages().stream()
                .map(ResultTvPackage::getOperatorName)
                .collect(Collectors.toList());
    }

    private Object[] mainTvPackagesName(ResultTvPackages input) {
        return input.getResultTvPackages().stream()
                .map(resultTvPackage -> resultTvPackage.getFilteredTvPackage().getName())
                .toArray();
    }

    private Object[] meetCriteriaTvPackageNames(ResultTvPackages input) {
        return input.getResultTvPackages().stream()
                .flatMap(resultTvPackage -> resultTvPackage.getFilteredTvPackage().getExtraTvPackagesWhichMeetCriteria().stream())
                .map(TvPackage::getName)
                .toArray();
    }

    private Object[] availableTvPackagesName(ResultTvPackages input) {
        return input.getResultTvPackages().stream()
                .flatMap(resultTvPackage -> resultTvPackage.getFilteredTvPackage().getExtraAvailableTvPackages().stream())
                .map(TvPackage::getName)
                .toArray();
    }

}
