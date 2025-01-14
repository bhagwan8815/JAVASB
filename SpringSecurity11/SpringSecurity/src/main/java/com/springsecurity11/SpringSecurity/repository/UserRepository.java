package com.springsecurity11.SpringSecurity.repository;

import com.springsecurity11.SpringSecurity.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {


    User findByUserName(String userName);

    User deleteByUserName(String userName);

}
