package com.example.masterdegree.config;

import com.example.masterdegree.models.*;
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
            List<Channel> channelList = new ArrayList<>();
            channelList.add(eurosport1); channelList.add(nsport); channelList.add(euroNews2);

            Channel euroNews = new Channel("EuroNews", "Informacja euro", "https://static.ftpn.pl/imgcache/640x365/c//uploads/cropit/15578520321d3a3fff9e90f37a15a1de260cc0b3720c0b1a5ce8bcb2750fb9fe38295e4141.jpg");
            Channel bloomberg = new Channel("Bloomberg", "Informacja bloom", "https://pbs.twimg.com/profile_images/1016326195221352450/KCcdUN0v.jpg");
            List<Channel> channelList1 = new ArrayList<>();
            channelList1.add(euroNews); channelList1.add(bloomberg);

            ChannelsGroupByCategory canalObjectSport = new ChannelsGroupByCategory(ObjectId.get(), "Sport", channelList);
            ChannelsGroupByCategory canalObjectInfo = new ChannelsGroupByCategory(ObjectId.get(), "Informacyjne", channelList1);
            List<ChannelsGroupByCategory> canalObjects = new ArrayList<>();
            canalObjects.add(canalObjectSport); canalObjects.add(canalObjectInfo);

            String canalPlusComfortLink = "https://sklep.pl.canalplus.com/oferta/comfortplus-ns";
            TvPackage tvPackageComfort = new TvPackage("Comfort +", 39.99, "main", canalPlusComfortLink, "24 miesiace", "0", canalObjects);
            TvPackage tvPackageSuperPremium = new TvPackage("SuperPremium +", 79.99, "main", canalPlusComfortLink, "24 miesiace", "0", canalObjects);
            List<TvPackage> tvPackageList = new ArrayList<>();
            tvPackageList.add(tvPackageComfort);
            tvPackageList.add(tvPackageSuperPremium);
            String canalPlusImg = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Platforma_Canal%2B.svg/1200px-Platforma_Canal%2B.svg.png";
            operatorsRepository.deleteAll();
            operatorsRepository.save(new Operator(ObjectId.get(), "Canal+", canalPlusImg, tvPackageList));
            operatorsRepository.save(new Operator(ObjectId.get(), "nc+", canalPlusImg, tvPackageList));
            operatorsRepository.save(new Operator(ObjectId.get(), "cyfrowy polsat+", canalPlusImg, tvPackageList));

            channelsRepository.deleteAll();
            channelsRepository.save(canalObjectSport);
            channelsRepository.save(canalObjectInfo);
            ChannelsGroupByCategory canalObjectFilm = new ChannelsGroupByCategory(ObjectId.get(), "Film", channelList1);
            channelsRepository.save(canalObjectFilm);

            ChannelsGroupByCategory canalObjectAnimal = new ChannelsGroupByCategory(ObjectId.get(), "Animal", channelList1);
            channelsRepository.save(canalObjectAnimal);
        };
    }

}
