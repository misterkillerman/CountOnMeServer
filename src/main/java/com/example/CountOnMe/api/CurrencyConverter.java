package com.example.CountOnMe.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class CurrencyConverter {
    private final String apiKey = "YOUR-API-KEY"; // replace with your real API key
    private final String baseUrl = "https://v6.exchangerate-api.com/v6/";

    @GetMapping("/api/exchange-rate")
    public ResponseEntity<String> getExchangeRate() {
        String url = baseUrl + apiKey + "/latest/USD";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Pass the third-party API response body back to the React frontend
            return ResponseEntity
                    .status(response.statusCode())
                    .body(response.body());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(500)
                    .body("Failed to fetch exchange rates.");
        }
    }
}
