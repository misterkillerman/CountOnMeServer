package com.example.CountOnMe.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.CountOnMe.model.Transactions;

@RepositoryRestResource(collectionResourceRel = "transactions", path = "transactions")
public interface TransactionsRepository extends MongoRepository<Transactions, String> {

    @Query("{ 'user': ?0, 'type': 'expense' }")
    List<Transactions> findExpensesByUser(@Param("user") String user);

    @Query("{ 'user': ?0, 'type': 'income' }")
    List<Transactions> findIncomeByUser(@Param("user") String user);

    List<Transactions> findAll();
    public long count();
}
