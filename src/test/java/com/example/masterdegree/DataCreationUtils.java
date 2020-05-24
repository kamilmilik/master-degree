package com.example.masterdegree;

import com.example.masterdegree.models.model.*;
import com.example.masterdegree.models.model.filter.FilteredTvPackage;
import com.example.masterdegree.models.model.filter.ResultTvPackage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DataCreationUtils {
    public static ResultTvPackage createResultTvPackage(String operatorId, String operatorName, FilteredTvPackage filteredTvPackage) {
        return new ResultTvPackage(operatorId, operatorName, "", filteredTvPackage);
    }

    public static Operator createOperator(String id, String name, List<MainTvPackage> mainTvPackages) {
        return new Operator(id, name, "", mainTvPackages);
    }

    public static TvPackage createTvPackage(String name) {
        return new TvPackage(name, "", 0d, "", "", "", "", "", new LinkedList<>());
    }

    public static TvPackage createTvPackage(String name, double price, List<Channel> channels) {
        return new TvPackage(name, "", price, "", "", "", "", "", channels);
    }

    public static TvPackage createTvPackage(String name, double price, String term, List<Channel> channels) {
        return new TvPackage(name, "", price, "", "", "", term, "", channels);
    }

    public static TvPackage createTvPackage(String name, String term) {
        return new TvPackage(name, "", 0.0d, "", "", "", term, "", new ArrayList<>());
    }

    public static TvPackage createTvPackage(String name, List<Channel> channels) {
        return new TvPackage(name, "", 0d, "", "", "", "", "", channels);
    }

    public static TvPackage createTvPackage(double price) {
        return new TvPackage("", "", price, "", "", "", "", "", new ArrayList<>());
    }

    public static TvPackage createTvPackage(List<Channel> channels) {
        return new TvPackage("", "", 0d, "", "", "", "", "", channels);
    }

    public static MainTvPackage createMainTvPackage(String name, List<Channel> channels, List<TvPackage> extraTvPackages) {
        return new MainTvPackage(name, "", 0d, "", "", "", "", "", channels, extraTvPackages);
    }

    public static MainTvPackage createMainTvPackage(String name, double price, String term, List<Channel> channels, List<TvPackage> extraTvPackages) {
        return new MainTvPackage(name, "", price, "", "", "", term, "", channels, extraTvPackages);
    }

    public static Criteria createCriteria(double price) {
        return Criteria.newCriteria(null, new Price(price), null, null);
    }

    public static Criteria createCriteriaOperators(List<String> operators) {
        return Criteria.newCriteria(operators, new Price(0d), null, null);
    }

    public static Criteria createCriteriaChannels(List<String> channelsName) {
        return Criteria.newCriteria(null, new Price(0d), channelsName, null);
    }

    public static Criteria createCriteria(double price, List<String> channelsName) {
        return Criteria.newCriteria(null, new Price(price), channelsName, null);
    }

    public static ResultTvPackage createResultTv(String operatorId, String operatorName, FilteredTvPackage filteredTvPackage) {
        return new ResultTvPackage(operatorId, operatorName, "", filteredTvPackage);
    }
}
