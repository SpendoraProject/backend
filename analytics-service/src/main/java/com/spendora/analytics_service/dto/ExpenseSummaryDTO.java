package com.spendora.analytics_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExpenseSummaryDTO {
    private String month;
    private double totalExpense;
    private double averageExpense;
    private double maxExpense;
    private double minExpense;
}
