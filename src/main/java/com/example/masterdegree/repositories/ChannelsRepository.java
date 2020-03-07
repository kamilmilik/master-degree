package com.example.masterdegree.repositories;

import com.example.masterdegree.models.ChannelsGroupByCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "channels", path = "channels")
public interface ChannelsRepository extends MongoRepository<ChannelsGroupByCategory, Integer> {
}
