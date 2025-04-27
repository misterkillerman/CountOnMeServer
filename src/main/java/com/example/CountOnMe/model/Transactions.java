package com.example.CountOnMe.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("Transactions")
public class Transactions {
    @Id
    private String id;
    private String type;
    private String category;
    private String description;
    private double amount;
    private String user;
    @Field("date")
    private Date date;


    public Transactions(String id, String type, String category, String description, double amount, String user,
            Date date) {
        this.id = id;
        this.type = type;
        this.category = category;
        this.description = description;
        this.amount = amount;
        this.user = user;
        this.date = date;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    @Override
    public String toString() {
        return "Transactions [id=" + id + ", type=" + type + ", category=" + category + ", description=" + description
                + ", amount=" + amount + ", user=" + user + ", date=" + date + "]";
    }


    
}
