package com.example.masterdegree.repositories;

import com.example.masterdegree.models.entity.Operator;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;


//@RepositoryRestResource(collectionResourceRel = "operators", path = "operators")
@Repository // Not use RepositoryRestResource. For OpenApi to work correctly.
public interface OperatorsRepository extends MongoRepository<Operator, String> {
}
