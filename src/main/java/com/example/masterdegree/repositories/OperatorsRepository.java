package com.example.masterdegree.repositories;

import com.example.masterdegree.models.model.Operator;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


//@RepositoryRestResource(collectionResourceRel = "operators", path = "operators")
@Repository // Not use RepositoryRestResource. For OpenApi to work correctly.
public interface OperatorsRepository extends MongoRepository<Operator, String> {
}
