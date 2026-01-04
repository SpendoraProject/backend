package com.spendora.notification_service.dto;

import lombok.Data;

@Data
public class ExpenseSummaryDTO {
    private String month;
    private double totalExpense;
    private double averageExpense;
    private double maxExpense;
    private double minExpense;
}
