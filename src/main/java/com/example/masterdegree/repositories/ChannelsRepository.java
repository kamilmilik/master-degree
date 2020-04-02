package com.example.masterdegree.repositories;

import com.example.masterdegree.models.model.ChannelsGroupByCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


//@RepositoryRestResource(collectionResourceRel = "channels", path = "channels")
@Repository // Not use RepositoryRestResource. For OpenApi to work correctly.
public interface ChannelsRepository extends MongoRepository<ChannelsGroupByCategory, Integer> {
}
