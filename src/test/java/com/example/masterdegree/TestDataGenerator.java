package com.example.masterdegree;

import com.example.masterdegree.models.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class TestDataGenerator {
    private Channel eurosport1 = new Channel("Eurosport1", "Kanal euro sport", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Eurosport_Logo.svg/1200px-Eurosport_Logo.svg.png");
    private Channel nsport = new Channel("nsport", "Kanal n sport", "http://ocdn.eu/images/program-tv/OWI7MDA_/518e09a968bd9238a72d45e37e9c8ac4.png");
    private Channel euroNews2 = new Channel("EuroNews2", "Informacja euro2", "https://static.ftpn.pl/imgcache/640x365/c//uploads/cropit/15578520321d3a3fff9e90f37a15a1de260cc0b3720c0b1a5ce8bcb2750fb9fe38295e4141.jpg");

    private List<Channel> channelListSport = new ArrayList<>();
    private List<Channel> channelListInfo = new ArrayList<>();
    private ChannelsGroupByCategory canalObjectSport = new ChannelsGroupByCategory(ObjectId.get(), "Sport", channelListSport);
    private ChannelsGroupByCategory canalObjectInfo = new ChannelsGroupByCategory(ObjectId.get(), "Informacja", channelListInfo);
    private List<ChannelsGroupByCategory> channelsGroupByCategory = new ArrayList<>();
    private TvPackage tvPackageComfort = new TvPackage("Comfort +", 39.99, "main", "https://sklep.pl.canalplus.com/oferta/comfortplus-ns", "24 miesiace", "0", channelsGroupByCategory);
    private TvPackage tvPackageSuperpremium = new TvPackage("Superpremium", 79.99, "main", "https://sklep.pl.canalplus.com/oferta/comfortplus-ns", "24 miesiace", "0", channelsGroupByCategory);
    private List<TvPackage> tvPackageList = new ArrayList<>();
    private Operator operatorCanalPlus = new Operator(ObjectId.get(), "Test Canal+", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Platforma_Canal%2B.svg/1200px-Platforma_Canal%2B.svg.png", tvPackageList);


    private Operator operatorCyfrowyPolsat = new Operator(ObjectId.get(), "Cyfrowy polsat", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Platforma_Canal%2B.svg/1200px-Platforma_Canal%2B.svg.png", tvPackageList);
    private List<ResultTvPackage> resultTvPackages = new ArrayList<>();

    public void generateOperatorWithOneTvPackage() {
        clearAll();
        channelListSport.add(eurosport1);
        channelListSport.add(nsport);
        channelListInfo.add(euroNews2);
        channelsGroupByCategory.add(canalObjectSport);
        channelsGroupByCategory.add(canalObjectInfo);
        tvPackageList.add(tvPackageComfort);
    }

    public void generateOperatorWithManyTvPackage() {
        clearAll();
        channelListSport.add(eurosport1);
        channelListSport.add(nsport);
        channelsGroupByCategory.add(canalObjectSport);
        channelsGroupByCategory.add(canalObjectInfo);
        tvPackageList.add(tvPackageComfort);
        tvPackageList.add(tvPackageSuperpremium);
    }


    public List<ResultTvPackage> generateResultTvPackage() {
        generateResultTvPackageForGivenOperator(operatorCanalPlus);
        generateResultTvPackageForGivenOperator(operatorCyfrowyPolsat);
        return resultTvPackages;
    }

    private void generateResultTvPackageForGivenOperator(Operator operator) {
        for (TvPackage tvPackage : operator.getTvPackages()) {
            resultTvPackages.add(new ResultTvPackage(operator.getId(), operator.getName(), operator.getImgSrc(), tvPackage));
        }
    }

    private void clearAll() {
        channelListSport.clear();
        channelsGroupByCategory.clear();
        tvPackageList.clear();
    }

    public TvPackage getTvPackageComfort() {
        return tvPackageComfort;
    }

    public TvPackage getTvPackageSuperpremium() {
        return tvPackageSuperpremium;
    }

    public List<TvPackage> getTvPackageList() {
        return tvPackageList;
    }

    public Operator getOperatorCanalPlus() {
        return operatorCanalPlus;
    }

    public Operator getOperatorCyfrowyPolsat() {
        return operatorCyfrowyPolsat;
    }

    public List<ResultTvPackage> getResultTvPackages() {
        return resultTvPackages;
    }
}
