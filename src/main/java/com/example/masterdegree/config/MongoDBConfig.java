package com.example.masterdegree.config;

import com.example.masterdegree.models.entity.*;
import com.example.masterdegree.repositories.ChannelsRepository;
import com.example.masterdegree.repositories.OperatorsRepository;
import org.bson.types.ObjectId;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

//@EnableMongoRepositories(basePackageClasses = UsersRepository.class)
@Configuration
public class MongoDBConfig {

    @Bean
    CommandLineRunner commandLineRunner(OperatorsRepository operatorsRepository, ChannelsRepository channelsRepository){
        return args -> {
            // ladowac z pliku json
            // endpoint aktualizujacy dana oferte
            Channel eurosport1 = new Channel("Eurosport1", "Kanal euro sport", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Eurosport_Logo.svg/1200px-Eurosport_Logo.svg.png");
            Channel nsport = new Channel("nsport", "Kanal n sport", "http://ocdn.eu/images/program-tv/OWI7MDA_/518e09a968bd9238a72d45e37e9c8ac4.png");
            Channel euroNews2 = new Channel("EuroNews2", "Informacja euro2", "https://static.ftpn.pl/imgcache/640x365/c//uploads/cropit/15578520321d3a3fff9e90f37a15a1de260cc0b3720c0b1a5ce8bcb2750fb9fe38295e4141.jpg");
            Channel eleven1 = new Channel("Eleven", "Informacja Eleven", "https://static.wirtualnemedia.pl/media/top/elevensportsnetwork-logo655.png");
            Channel eleven2 = new Channel("Eleven 2", "Informacja Eleven 2", "https://static.wirtualnemedia.pl/media/top/elevensportsnetwork-logo655.png");

            Channel hbo = new Channel("Hbo", "Informacja Hbo", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSr_3rYMnQujDhcHH5a-xLDZXQoqvIwzQtd7cnpMGaz2_K8ny-5");
            Channel hbo2 = new Channel("Hbo2", "Informacja Hbo2", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSr_3rYMnQujDhcHH5a-xLDZXQoqvIwzQtd7cnpMGaz2_K8ny-5");

            Channel euroNews = new Channel("EuroNews", "Informacja euro", "https://static.ftpn.pl/imgcache/640x365/c//uploads/cropit/15578520321d3a3fff9e90f37a15a1de260cc0b3720c0b1a5ce8bcb2750fb9fe38295e4141.jpg");
            Channel bloomberg = new Channel("Bloomberg", "Informacja bloom", "https://pbs.twimg.com/profile_images/1016326195221352450/KCcdUN0v.jpg");

            Channel tvn = new Channel("tvn", "Informacja tvn", "https://upload.wikimedia.org/wikipedia/commons/8/83/TVN.png");
            List<Channel> comfortCanalPlusChannels = new ArrayList<>();
            comfortCanalPlusChannels.add(eurosport1);comfortCanalPlusChannels.add(nsport); comfortCanalPlusChannels.add(euroNews);comfortCanalPlusChannels.add(bloomberg);

            List<Channel> superPremiumCanalPlusChannels = new ArrayList<>(comfortCanalPlusChannels);
            superPremiumCanalPlusChannels.add(tvn);

            List<Channel> extraElevenCanalPlusChannels = new ArrayList<>();
            extraElevenCanalPlusChannels.add(eleven1); extraElevenCanalPlusChannels.add(eleven2);

            List<Channel> extraHboCanalPlusChannels = new ArrayList<>();
            extraHboCanalPlusChannels.add(hbo);extraHboCanalPlusChannels.add(hbo2);

            String canalPlusComfortLink = "https://sklep.pl.canalplus.com/oferta/comfortplus-ns";
            List<TvPackage> extraCanalPlusComfortPackage = new ArrayList<>();
            extraCanalPlusComfortPackage.add(new TvPackage("Eleven sports +", 15.00, "extra", "https://static.wirtualnemedia.pl/media/top/elevensportsnetwork-logo655.png", canalPlusComfortLink, "24 miesiace", "0", extraElevenCanalPlusChannels));
            extraCanalPlusComfortPackage.add(new TvPackage("Hbo", 20.00, "extra", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSr_3rYMnQujDhcHH5a-xLDZXQoqvIwzQtd7cnpMGaz2_K8ny-5", canalPlusComfortLink, "24 miesiace", "0", extraHboCanalPlusChannels));


            List<TvPackage> extraCanalPlusSuperPremiumPackage = new ArrayList<>();
            extraCanalPlusSuperPremiumPackage.add(new TvPackage("Eleven sports", 11.00, "extra", "https://static.wirtualnemedia.pl/media/top/elevensportsnetwork-logo655.png", canalPlusComfortLink, "24 miesiace", "0", extraElevenCanalPlusChannels));
            MainTvPackage tvPackageComfort = new MainTvPackage("Comfort +", 39.99, "main", "",canalPlusComfortLink, "24 miesiace", "0", comfortCanalPlusChannels, extraCanalPlusComfortPackage);
            MainTvPackage tvPackageSuperPremium = new MainTvPackage("SuperPremium +", 79.99, "main","", canalPlusComfortLink, "24 miesiace", "0", superPremiumCanalPlusChannels, extraCanalPlusSuperPremiumPackage);
            List<MainTvPackage> mainTvPackagesList = new ArrayList<>();
            mainTvPackagesList.add(tvPackageComfort);
            mainTvPackagesList.add(tvPackageSuperPremium);
            String canalPlusImg = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Platforma_Canal%2B.svg/1200px-Platforma_Canal%2B.svg.png";
            operatorsRepository.deleteAll();
            operatorsRepository.save(new Operator(ObjectId.get(), "Canal+", canalPlusImg, mainTvPackagesList));
            operatorsRepository.save(new Operator(ObjectId.get(), "nc+", canalPlusImg, mainTvPackagesList));
            operatorsRepository.save(new Operator(ObjectId.get(), "cyfrowy polsat+", canalPlusImg, mainTvPackagesList));

            List<Channel> channelList1 = new ArrayList<>();
            channelList1.add(euroNews); channelList1.add(bloomberg);channelList1.add(hbo);channelList1.add(hbo2);

            List<Channel> channelList = new ArrayList<>();
            channelList.add(eurosport1); channelList.add(nsport); channelList.add(euroNews2);channelList.add(eleven1);channelList.add(eleven2);

            ChannelsGroupByCategory canalObjectSport = new ChannelsGroupByCategory(ObjectId.get(), "Sport", channelList);
            ChannelsGroupByCategory canalObjectInfo = new ChannelsGroupByCategory(ObjectId.get(), "Informacyjne", channelList1);
            ChannelsGroupByCategory canalObjectFilm = new ChannelsGroupByCategory(ObjectId.get(), "Film", channelList1);
            ChannelsGroupByCategory canalObjectAnimal = new ChannelsGroupByCategory(ObjectId.get(), "Animal", channelList1);

            channelsRepository.deleteAll();
            channelsRepository.save(canalObjectSport);
            channelsRepository.save(canalObjectInfo);


            channelsRepository.save(canalObjectFilm);

            channelsRepository.save(canalObjectAnimal);
        };
    }

}
