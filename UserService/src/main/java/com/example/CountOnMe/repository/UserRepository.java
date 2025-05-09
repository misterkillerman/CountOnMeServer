package com.example.CountOnMe.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.CountOnMe.model.User;


@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends MongoRepository<User, String> {

	User findUserByName(@Param("name") String name);
	
    User findUserByEmail(@Param("email") String email);
    
    List<User> findAll();
    
    public long count();
} 