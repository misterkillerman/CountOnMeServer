package com.example.CountOnMe.Controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.CountOnMe.model.Transactions;
import com.example.CountOnMe.repository.TransactionsRepository;


@RestController
@RequestMapping("/transactions")
public class TransactionController {
        private final TransactionsRepository transactionsRepository;

    public TransactionController(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    @GetMapping("/getUserExpenseCategory")
    public ResponseEntity<List<Map<String, Object>>> getTop3ExpensesAndAmount(
        @RequestParam("user") String user
    ){
        List<Transactions> transactionsList = transactionsRepository.findExpensesByUser(user);
        List<Map<String, Object>> top3CatAndAmt = new ArrayList<>();

        if (transactionsList != null && !transactionsList.isEmpty()) {
            top3CatAndAmt = transactionsList.stream()
                .collect(Collectors.groupingBy(
                    Transactions::getCategory,
                    Collectors.summingDouble(Transactions::getAmount)
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .map(entry -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("category", entry.getKey());
                    map.put("amount", entry.getValue());
                    return map;
                })
                .collect(Collectors.toList());

        }
        return ResponseEntity.ok().body(top3CatAndAmt);    
    }

    @GetMapping("/getUserIncomeCategory")
    public ResponseEntity<List<Map<String, Object>>> getTop3IncomeAndAmount(
        @RequestParam("user") String user
    ){
        List<Transactions> transactionsList = transactionsRepository.findIncomeByUser(user);
        List<Map<String, Object>> top3CatAndAmt = new ArrayList<>();

        if (transactionsList != null && !transactionsList.isEmpty()) {
            top3CatAndAmt = transactionsList.stream()
                .collect(Collectors.groupingBy(
                    Transactions::getCategory,
                    Collectors.summingDouble(Transactions::getAmount)
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .map(entry -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("category", entry.getKey());
                    map.put("amount", entry.getValue());
                    return map;
                })
                .collect(Collectors.toList());

        }
        return ResponseEntity.ok().body(top3CatAndAmt);    
    }
}
