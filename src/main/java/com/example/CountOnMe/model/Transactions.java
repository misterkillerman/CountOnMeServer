package com.example.CountOnMe.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Transactions")
public class Transactions {
    @Id
    private String id;
    private String type;
    private String category;
    private String description;
    private double amount;

    public Transactions(String id, String type, String category, String description, double amount) {
        super();
        this.id = id;
        this.type = type;
        this.category = category;
        this.description = description;
        this.amount = amount;
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
    @Override
    public String toString() {
        return "Transactions [id=" + id + ", type=" + type + ", category=" + category + ", description=" + description
                + ", amount=" + amount + "]";
    }

    
}
