package com.example.masterdegree.repositories;

import com.example.masterdegree.models.ChannelsGroupByCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


@RepositoryRestResource(collectionResourceRel = "channels", path = "channels")
public interface ChannelsRepository extends MongoRepository<ChannelsGroupByCategory, Integer> {
}
