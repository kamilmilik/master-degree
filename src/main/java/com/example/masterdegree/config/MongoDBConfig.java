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
            operatorsRepository.deleteAll();
            Channel eurosport1 = new Channel("Eurosport1", "Kanal euro sport", "src img");
            Channel nsport = new Channel("nsport", "Kanal n sport", "src img");
            List<Channel> channelList = new ArrayList<>();
            channelList.add(eurosport1); channelList.add(nsport);

            Channel euroNews = new Channel("EuroNews", "Informacja euro", "src");
            Channel bloomberg = new Channel("Bloomberg", "Informacja bloom", "src");
            List<Channel> channelList1 = new ArrayList<>();
            channelList1.add(euroNews); channelList1.add(bloomberg);

            ChannelObject canalObjectSport = new ChannelObject("Sport", channelList);
            ChannelObject canalObjectInfo = new ChannelObject("Informacyjne", channelList1);
            List<ChannelObject> canalObjects = new ArrayList<>();
            canalObjects.add(canalObjectSport); canalObjects.add(canalObjectInfo);

            TvPackage tvPackageComfort = new TvPackage("Comfort +", 39.99, "main", canalObjects);
            List<TvPackage> tvPackageList = new ArrayList<>();
            tvPackageList.add(tvPackageComfort);
            operatorsRepository.save(new Operator(ObjectId.get(), "Canal+", tvPackageList));
            operatorsRepository.save(new Operator(ObjectId.get(), "nc+", tvPackageList));

            channelsRepository.deleteAll();
            channelsRepository.save(canalObjectSport);
            channelsRepository.save(canalObjectInfo);
        };
    }

}
