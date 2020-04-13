package com.example.masterdegree.core.strategyfilters;

import com.example.masterdegree.models.dto.ChannelDto;
import com.example.masterdegree.models.dto.FilteredTvPackageResponseDto;
import com.example.masterdegree.models.dto.ResultTvPackageResponseDto;
import com.example.masterdegree.models.dto.TvPackageResponseDto;
import com.example.masterdegree.models.model.Channel;
import com.example.masterdegree.models.model.Criteria;
import com.example.masterdegree.models.model.Price;
import com.example.masterdegree.models.model.TvPackage;
import com.example.masterdegree.models.model.filter.FilteredTvPackage;
import com.example.masterdegree.models.model.filter.ResultTvPackage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DataCreationUtils {
    public static ResultTvPackage createResultTvPackage(String operatorId, String operatorName, FilteredTvPackage filteredTvPackage) {
        return new ResultTvPackage(operatorId, operatorName, "", filteredTvPackage);
    }

    public static TvPackage createTvPackage(String name) {
        return new TvPackage(name, "", 0d, "", "", "", "", "", new LinkedList<>());
    }

    public static TvPackage createTvPackage(String name,double price, List<Channel> channels) {
        return new TvPackage(name, "", price, "", "", "", "", "", channels);
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

    public static Criteria createCriteria(double price) {
        return Criteria.newCriteria(null, new Price(price), null, null);
    }

    public static Criteria createCriteria(List<Channel> channels) {
        return Criteria.newCriteria(null, new Price(0d), channels, null);
    }

    public static Criteria createCriteria(double price, List<Channel> channels) {
        return Criteria.newCriteria(null, new Price(price), channels, null);
    }

    public static ResultTvPackageResponseDto createResultTvPackageDto(String operatorId, String operatorName, FilteredTvPackageResponseDto filteredTvPackage) {
        return new ResultTvPackageResponseDto(operatorId, operatorName, "", filteredTvPackage);
    }

    public static TvPackageResponseDto createTvPackageDto(String name) {
        return new TvPackageResponseDto(name, "", 0d, "", "", "", "", new LinkedList<>());
    }

    public static TvPackageResponseDto createTvPackageDto(String name, List<ChannelDto> channels) {
        return new TvPackageResponseDto(name, "", 0d, "", "", "", "", channels);
    }

    public static ChannelDto createChannelDto(String name) {
        return new ChannelDto(name, "");
    }
}
