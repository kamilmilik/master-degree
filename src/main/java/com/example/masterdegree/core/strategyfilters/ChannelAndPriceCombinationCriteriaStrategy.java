package com.example.masterdegree.core.strategyfilters;

import com.example.masterdegree.models.model.Criteria;
import com.example.masterdegree.models.model.TvPackage;
import com.example.masterdegree.models.model.filter.FilteredTvPackage;
import com.example.masterdegree.models.model.filter.ResultTvPackage;
import org.paukov.combinatorics3.Generator;

import java.util.*;
import java.util.stream.Collectors;

public class ChannelAndPriceCombinationCriteriaStrategy implements CriteriaStrategy {

    private final Criteria criteria;

    public ChannelAndPriceCombinationCriteriaStrategy(Criteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public List<ResultTvPackage> getFilteredResult(List<ResultTvPackage> resultTvPackage) {
        return getResultFilteredByChannelsCombinationAndPrice(resultTvPackage);
    }

    private List<ResultTvPackage> getResultFilteredByChannelsCombinationAndPrice(List<ResultTvPackage> resultTvPackages) {
        if (criteria.hasAnyChannelsCriteria()) {
            resultTvPackages.forEach(resultTvPackage -> {
                List<TvPackage> meetCriteriaTvPackages = resultTvPackage.getFilteredTvPackage().getExtraTvPackagesWhichMeetCriteria();
                if (resultTvPackage.getFilteredTvPackage().hasExtraTvPackagesWhichMeetCriteria()) {
                    List<List<TvPackage>> combinedTvPackages = createAllCombinationsFromGivenList(meetCriteriaTvPackages);
                    combinedTvPackages = getFilteredCombinationsWhichSumOfPricePlusGivenPriceIsBetweenPriceCriteria(resultTvPackage.getFilteredTvPackage().getPrice(), combinedTvPackages);
                    combinedTvPackages = checkIfCombinedTvPackagesContainsAllCriteriaChannels(combinedTvPackages);
                    List<TvPackage> meetAllCriteriaTvPackages = sortAndGetCombinedTvPackagesBySumOfPrice(combinedTvPackages);
                    resultTvPackage.getFilteredTvPackage().replaceExtraTvPackagesWithGivenList(meetAllCriteriaTvPackages);
                }
            });
            resultTvPackages = removeResultsWhichNotMeetCriteria(resultTvPackages);
            resultTvPackages = removeExtraAvailableTvPackagesWhichAreInMeetCriteriaPackages(resultTvPackages);
        }
        return resultTvPackages;
    }

    List<List<TvPackage>> createAllCombinationsFromGivenList(List<TvPackage> list) {
        List<List<TvPackage>> combinedTvPackages = new ArrayList<>();
        for (int length = 1; length <= list.size(); length++) {
            combinedTvPackages.addAll(createCombinationsOfGivenTvPackagesWithGivenLength(list, length));
        }
        return combinedTvPackages;
    }

    List<ResultTvPackage> removeExtraAvailableTvPackagesWhichAreInMeetCriteriaPackages(List<ResultTvPackage> resultTvPackages) {
        resultTvPackages.forEach(resultTvPackage -> {
            FilteredTvPackage filteredTvPackage = resultTvPackage.getFilteredTvPackage();
            filteredTvPackage.removeGivenTvPackagesFromExtraTvPackages(filteredTvPackage.getExtraTvPackagesWhichMeetCriteria());
        });
        return resultTvPackages;
    }

    List<ResultTvPackage> removeResultsWhichNotMeetCriteria(List<ResultTvPackage> resultTvPackages) {
        resultTvPackages.removeIf(resultTvPackage -> !resultTvPackage.getFilteredTvPackage().hasExtraTvPackagesWhichMeetCriteria());
        return resultTvPackages;
    }

    List<List<TvPackage>> checkIfCombinedTvPackagesContainsAllCriteriaChannels(List<List<TvPackage>> combinedTvPackages) {
        criteria.getChannels().forEach(channel -> {
            combinedTvPackages.removeIf(tvPackages ->
                    tvPackages.stream().noneMatch(tvPackage ->
                            tvPackage.getChannels().stream().anyMatch(tvPackageChannel ->
                                    tvPackageChannel.isTheSame(channel)
                            )
                    )
            );
        });
        return combinedTvPackages;
    }

    private List<List<TvPackage>> createCombinationsOfGivenTvPackagesWithGivenLength(List<TvPackage> tvPackages, int length) {
        return Generator.combination(tvPackages).simple(length).stream().collect(Collectors.toList());
    }

    List<List<TvPackage>> getFilteredCombinationsWhichSumOfPricePlusGivenPriceIsBetweenPriceCriteria(double mainTvPackagePrice, List<List<TvPackage>> combinedTvPackages) {
        return combinedTvPackages.stream().filter(tvPackages -> {
            double sumOfCombinedTvPackagePrice = tvPackages.stream().mapToDouble(TvPackage::getPrice).sum();
            return criteria.getPrice().isBetween(sumOfCombinedTvPackagePrice + mainTvPackagePrice);
        }).collect(Collectors.toList());
    }

    List<TvPackage> sortAndGetCombinedTvPackagesBySumOfPrice(List<List<TvPackage>> combinedTvPackages) {
        Optional<List<TvPackage>> sortedList = combinedTvPackages.stream().min(Comparator.comparing(tvPackages -> tvPackages.stream().mapToDouble(TvPackage::getPrice).sum()));
        return sortedList.orElse(new LinkedList<>());

    }
}
