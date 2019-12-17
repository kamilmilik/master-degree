package com.example.masterdegree.repositories;

import com.example.masterdegree.models.Operator;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OperatorsRepository extends MongoRepository<Operator, String> {
}
