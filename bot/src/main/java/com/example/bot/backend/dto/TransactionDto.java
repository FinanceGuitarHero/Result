package com.example.bot.backend.dto;

public record TransactionDto(String userId, String amount, boolean type, String category,
                             String description, String bankName) {
}
