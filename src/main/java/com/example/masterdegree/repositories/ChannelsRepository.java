package com.example.masterdegree.repositories;

import com.example.masterdegree.models.ChannelsGroupByCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelsRepository extends MongoRepository<ChannelsGroupByCategory, Integer> {
}
