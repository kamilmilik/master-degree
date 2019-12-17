package com.example.masterdegree.repositories;

import com.example.masterdegree.models.Channel;
import com.example.masterdegree.models.ChannelObject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelsRepository extends MongoRepository<ChannelObject, Integer> {
}
