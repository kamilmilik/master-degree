package com.example.masterdegree.repositories;

import com.example.masterdegree.models.entity.Operator;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "operators", path = "operators")
public interface OperatorsRepository extends MongoRepository<Operator, String> {
}
