package com.example.CountOnMe.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.CountOnMe.model.Transactions;

@CrossOrigin(origins = "*")
@RepositoryRestResource(collectionResourceRel = "transactions", path = "transactions")
public interface TransactionsRepository extends MongoRepository<Transactions, String> {

    @Query("{ 'user': ?0}")
    List<Transactions> findTransactionsByUser(@Param("user") String user);

    @Query(value = "{ 'user': ?0, 'date': { '$gte': ?1, '$lt': ?2 } }", sort = "{ 'date': -1 }")
    List<Transactions> findTransactionsByUserByDate(
        @Param("user") String user,
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate
    );

    @Query("{ 'user': ?0, 'type': 'expense' }")
    List<Transactions> findExpensesByUser(@Param("user") String user);

    @Query("{ 'user': ?0, 'type': 'income' }")
    List<Transactions> findIncomeByUser(@Param("user") String user);

    @Query("{ 'user': ?0, 'type': 'expense', 'date': { '$gte': ?1, '$lt': ?2 } }")
    List<Transactions> findExpensesByUserAndMonth(
        @Param("user") String user,
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate
    );

    @Query("{ 'user': ?0, 'type': 'income', 'date': { $gte: ?1, $lte: ?2 } }")
    List<Transactions> findIncomeByUserAndMonth(
        @Param("user") String user, 
        @Param("startDate") Date startDate, 
        @Param("endDate") Date endDate
    );

    List<Transactions> findByUserAndTypeAndCategoryAndAmount(
        @Param("user") String user, 
        @Param("type") String type, 
        @Param("category") String category, 
        @Param("amount") Double amount
    );

    List<Transactions> findAll();
    public long count();
}
