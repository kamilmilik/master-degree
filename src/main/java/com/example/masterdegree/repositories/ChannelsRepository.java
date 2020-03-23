package com.example.masterdegree.repositories;

import com.example.masterdegree.models.entity.ChannelsGroupByCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;


//@RepositoryRestResource(collectionResourceRel = "channels", path = "channels")
@Repository // Not use RepositoryRestResource. For OpenApi to work correctly.
public interface ChannelsRepository extends MongoRepository<ChannelsGroupByCategory, Integer> {
}
