package com.example.masterdegree.core.strategyfilters;

import com.example.masterdegree.models.model.Criteria;
import com.example.masterdegree.models.model.TvPackage;
import com.example.masterdegree.models.model.filter.FilteredTvPackage;
import com.example.masterdegree.models.model.filter.ResultTvPackage;
import org.paukov.combinatorics3.Generator;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChannelAndPriceCombinationCriteriaStrategy implements CriteriaStrategy {

    private final ChannelCriteriaStrategy channelCriteriaStrategy;
    private final Criteria criteria;

    public ChannelAndPriceCombinationCriteriaStrategy(Criteria criteria) {
        this.criteria = criteria;
        channelCriteriaStrategy = new ChannelCriteriaStrategy(criteria);
    }

    @Override
    public List<ResultTvPackage> getFilteredResult(List<ResultTvPackage> resultTvPackage) {
        return getResultFilteredByChannelsCombinationAndPrice(resultTvPackage);
    }

    private List<ResultTvPackage> getResultFilteredByChannelsCombinationAndPrice(List<ResultTvPackage> resultTvPackages) {
        if (criteria.hasAnyChannelsCriteria()) {
            List<ResultTvPackage> resultsToDelete = new LinkedList<>();
            resultTvPackages.forEach(resultTvPackage -> {
                if (resultTvPackage.getFilteredTvPackage().hasExtraTvPackagesWhichMeetCriteria()) {
                    List<TvPackage> meetCriteriaTvPackages = resultTvPackage.getFilteredTvPackage().getExtraTvPackagesWhichMeetCriteria();
                    List<List<TvPackage>> combinedTvPackages = createAllCombinationsFromGivenList(meetCriteriaTvPackages);
                    combinedTvPackages = getFilteredCombinationsWhichSumOfPricePlusGivenPriceIsBetweenPriceCriteria(resultTvPackage.getFilteredTvPackage().getPrice(), combinedTvPackages);
                    combinedTvPackages = checkIfCombinedTvPackagesContainsAllCriteriaChannelsExceptChannelsFromMainTvPackage(resultTvPackage, combinedTvPackages);
                    List<TvPackage> meetAllCriteriaTvPackages = sortAndGetCombinedTvPackagesBySumOfPrice(combinedTvPackages);
                    if (!hasMeetAllCriteriaTvPackages(meetAllCriteriaTvPackages)) {
                        resultsToDelete.add(resultTvPackage);
                    }
                    resultTvPackage.getFilteredTvPackage().replaceExtraTvPackagesWithGivenList(meetAllCriteriaTvPackages);
                }
            });
            resultTvPackages = removeResultsWhichNotMeetCriteria(resultTvPackages, resultsToDelete);
            resultTvPackages = removeExtraAvailableTvPackagesWhichAreInMeetCriteriaPackages(resultTvPackages);
        }
        return resultTvPackages;
    }

    boolean hasMeetAllCriteriaTvPackages(List<TvPackage> meetAllCriteriaTvPackages) {
        return meetAllCriteriaTvPackages.size() > 0;
    }

    List<List<TvPackage>> createAllCombinationsFromGivenList(List<TvPackage> list) {
        List<List<TvPackage>> combinedTvPackages = new LinkedList<>();
        for (int length = 1; length <= list.size(); length++) {
            combinedTvPackages.addAll(createCombinationsOfGivenTvPackagesWithGivenLength(list, length));
        }
        return combinedTvPackages;
    }

    List<ResultTvPackage> removeExtraAvailableTvPackagesWhichAreInMeetCriteriaPackages(List<ResultTvPackage> resultTvPackages) {
        resultTvPackages.forEach(resultTvPackage -> {
            FilteredTvPackage filteredTvPackage = resultTvPackage.getFilteredTvPackage();
            filteredTvPackage.removeGivenTvPackagesFromExtraTvPackages(filteredTvPackage.getExtraTvPackagesWhichMeetCriteria());
            filteredTvPackage.removeGivenTvPackageFromExtraTvPackagesIfContainsAllChannels(filteredTvPackage.getExtraTvPackagesWhichMeetCriteria());
        });
        return resultTvPackages;
    }

    List<ResultTvPackage> removeResultsWhichNotMeetCriteria(List<ResultTvPackage> resultTvPackages, List<ResultTvPackage> resultTvPackage) {
        resultTvPackages.removeAll(resultTvPackage);
        return resultTvPackages;
    }

    List<List<TvPackage>> checkIfCombinedTvPackagesContainsAllCriteriaChannelsExceptChannelsFromMainTvPackage(ResultTvPackage resultTvPackage, List<List<TvPackage>> combinedTvPackages) {
        criteria.getChannels().forEach(channel -> {
            boolean isSelectedChannelInMainTvPackage = channelCriteriaStrategy.searchChannelInMainTvPackage(resultTvPackage, channel);
            if (!isSelectedChannelInMainTvPackage) {
                combinedTvPackages.removeIf(tvPackages ->
                        tvPackages.stream().noneMatch(tvPackage ->
                                tvPackage.getChannels().stream().anyMatch(tvPackageChannel ->
                                        tvPackageChannel.isTheSame(channel)
                                )
                        )
                );
            }
        });
        return combinedTvPackages;
    }

    private List<List<TvPackage>> createCombinationsOfGivenTvPackagesWithGivenLength(List<TvPackage> tvPackages, int length) {
        return Generator.combination(tvPackages).simple(length).stream().collect(Collectors.toList());
    }

    List<List<TvPackage>> getFilteredCombinationsWhichSumOfPricePlusGivenPriceIsBetweenPriceCriteria(double mainTvPackagePrice, List<List<TvPackage>> combinedTvPackages) {
        return combinedTvPackages.stream().filter(tvPackages -> {
            double sumOfCombinedTvPackagePrice = tvPackages.stream().mapToDouble(TvPackage::getPrice).sum();
            return criteria.getPrice().isGreaterThan(sumOfCombinedTvPackagePrice + mainTvPackagePrice);
        }).collect(Collectors.toList());
    }

    List<TvPackage> sortAndGetCombinedTvPackagesBySumOfPrice(List<List<TvPackage>> combinedTvPackages) {
        Optional<List<TvPackage>> sortedList = combinedTvPackages.stream().min(Comparator.comparing(tvPackages -> tvPackages.stream().mapToDouble(TvPackage::getPrice).sum()));
        return sortedList.orElse(new LinkedList<>());

    }
}
