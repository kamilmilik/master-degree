package com.example.masterdegree.trash;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends MongoRepository<Users, Integer> {
//    Users findBy_id(Integer id);
}
