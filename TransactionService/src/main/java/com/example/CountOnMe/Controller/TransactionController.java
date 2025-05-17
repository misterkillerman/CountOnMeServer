package com.example.CountOnMe.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.CountOnMe.model.Transactions;
import com.example.CountOnMe.repository.TransactionsRepository;

@CrossOrigin
@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionsRepository transactionsRepository;

    public TransactionController(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    private Calendar initCal() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    private Date getStartOfToday() {
        Calendar cal = initCal(); 
        return cal.getTime();
    }

    private Date getEndOfToday() {
        Calendar cal = initCal();
        cal.add(Calendar.DAY_OF_MONTH, 1); // move to tomorrow 00:00
        return cal.getTime();
    }

    private Date getStartOfWeek() {
        Calendar cal = initCal();
        // Find out today
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        // Calculate how many days to subtract to reach previous Monday
        int daysFromMonday = (dayOfWeek + 5) % 7; // Sunday (1) -> 6, Monday (2) -> 0, etc.

        cal.add(Calendar.DAY_OF_MONTH, -daysFromMonday);
        return cal.getTime();
    }
    
    private Date getEndOfWeek() {
        Calendar cal = initCal();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.add(Calendar.WEEK_OF_YEAR, 1); // Move to next Monday
        return cal.getTime();
    }

    private Date getStartOfMonth() {
        Calendar cal = initCal();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    private Date getEndOfMonth() {
        Calendar cal = initCal();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH, 1);
        return cal.getTime();
    }

    @GetMapping("/getTopExpensesCategoryInMonth")
    public ResponseEntity<List<Map<String, Object>>> getTop3ExpensesAndAmountInMonth(
        @RequestParam("user") String user
    ){
        List<Transactions> transactionsList = transactionsRepository.findExpensesByUserAndMonth(user, getStartOfMonth(), getEndOfMonth());
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

    @GetMapping("/getTopIncomeCategoryInMonth")
    public ResponseEntity<List<Map<String, Object>>> getTop3IncomeAndAmountInMonth(
        @RequestParam("user") String user
    ){
        List<Transactions> transactionsList = transactionsRepository.findIncomeByUserAndMonth(user, getStartOfMonth(), getEndOfMonth());
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

    @GetMapping("/transactionsToday")
    public ResponseEntity<List<Transactions>> getTransactionsToday(
        @RequestParam("user") String user
    ) {
        List<Transactions> transactionsList = transactionsRepository.findTransactionsByUserByDate(user, getStartOfToday(), getEndOfToday());
        return ResponseEntity.ok().body(transactionsList);    
    }

    @GetMapping("/transactionsWeek")
    public ResponseEntity<List<Transactions>> getTransactionsWeek(
        @RequestParam("user") String user
    ) {
        List<Transactions> transactionsList = transactionsRepository.findTransactionsByUserByDate(user, getStartOfWeek(), getEndOfWeek());
        return ResponseEntity.ok().body(transactionsList);    
    }

    @GetMapping("/transactionsMonth")
    public ResponseEntity<List<Transactions>> getTransactionsMonth(
        @RequestParam("user") String user
    ) {
        List<Transactions> transactionsList = transactionsRepository.findTransactionsByUserByDate(user, getStartOfMonth(), getEndOfMonth());
        return ResponseEntity.ok().body(transactionsList);    
    }
}
