package com.example.masterdegree;

import com.example.masterdegree.models.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class TestDataGenerator {
    private Channel eurosport1 = new Channel("Eurosport1", "Kanal euro sport", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Eurosport_Logo.svg/1200px-Eurosport_Logo.svg.png");
    private Channel nsport = new Channel("nsport", "Kanal n sport", "http://ocdn.eu/images/program-tv/OWI7MDA_/518e09a968bd9238a72d45e37e9c8ac4.png");
    private Channel euroNews2 = new Channel("EuroNews2", "Informacja euro2", "https://static.ftpn.pl/imgcache/640x365/c//uploads/cropit/15578520321d3a3fff9e90f37a15a1de260cc0b3720c0b1a5ce8bcb2750fb9fe38295e4141.jpg");

    Channel eleven1 = new Channel("Eleven", "Informacja Eleven", "https://static.wirtualnemedia.pl/media/top/elevensportsnetwork-logo655.png");
    Channel eleven2 = new Channel("Eleven 2", "Informacja Eleven 2", "https://static.wirtualnemedia.pl/media/top/elevensportsnetwork-logo655.png");

    List<Channel> extraElevenCanalPlusChannels = new ArrayList<>();
    TvPackage tvPackageEleven = new TvPackage("Eleven sports +", 15.00, "extra", "https://sklep.pl.canalplus.com/oferta/comfortplus-ns", "24 miesiace", "0", extraElevenCanalPlusChannels);
    List<TvPackage> extraTvPackagesList = new ArrayList<>();


    private List<Channel> channelList = new ArrayList<>();

    private TvPackage tvPackageComfort = new TvPackage("Comfort +", 39.99, "main", "https://sklep.pl.canalplus.com/oferta/comfortplus-ns", "24 miesiace", "0", channelList);
    private TvPackage tvPackageSuperpremium = new TvPackage("Superpremium", 79.99, "main", "https://sklep.pl.canalplus.com/oferta/comfortplus-ns", "24 miesiace", "0", channelList);
    private List<TvPackage> mainTvPackagesList = new ArrayList<>();
    private Operator operatorCanalPlus = new Operator(ObjectId.get(), "Test Canal+", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Platforma_Canal%2B.svg/1200px-Platforma_Canal%2B.svg.png", mainTvPackagesList, extraTvPackagesList);


    private Operator operatorCyfrowyPolsat = new Operator(ObjectId.get(), "Cyfrowy polsat", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Platforma_Canal%2B.svg/1200px-Platforma_Canal%2B.svg.png", mainTvPackagesList, extraTvPackagesList);
    private List<ResultTvPackage> resultTvPackages = new ArrayList<>();

    public void generateOperatorWithOneTvPackage() {
        clearAll();
        channelList.add(eurosport1);
        channelList.add(nsport);
        channelList.add(euroNews2);
        extraElevenCanalPlusChannels.add(eleven1); extraElevenCanalPlusChannels.add(eleven2);
        extraTvPackagesList.add(tvPackageEleven);

        mainTvPackagesList.add(tvPackageComfort);
    }

    public void generateOperatorWithManyTvPackage() {
        clearAll();
        channelList.add(eurosport1);
        channelList.add(nsport);
        mainTvPackagesList.add(tvPackageComfort);
        mainTvPackagesList.add(tvPackageSuperpremium);
    }


    public List<ResultTvPackage> generateResultTvPackage() {
        generateResultTvPackageForGivenOperator(operatorCanalPlus);
        generateResultTvPackageForGivenOperator(operatorCyfrowyPolsat);
        return resultTvPackages;
    }

    private void generateResultTvPackageForGivenOperator(Operator operator) {
        for (TvPackage tvPackage : operator.getMainTvPackages()) {
            resultTvPackages.add(new ResultTvPackage(operator.getId(), operator.getName(), operator.getImgSrc(), tvPackage, operator.getExtraTvPackages()));
        }
    }

    private void clearAll() {
        channelList.clear();
        mainTvPackagesList.clear();
    }

    public TvPackage getTvPackageComfort() {
        return tvPackageComfort;
    }

    public TvPackage getTvPackageSuperpremium() {
        return tvPackageSuperpremium;
    }

    public List<TvPackage> getMainTvPackagesList() {
        return mainTvPackagesList;
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