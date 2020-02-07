package com.example.masterdegree.repositories;

import com.example.masterdegree.models.Operator;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "operators", path = "operators")
public interface OperatorsRepository extends MongoRepository<Operator, String> {
}
