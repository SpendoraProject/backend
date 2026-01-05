package com.spendora.analytics_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExpenseSummaryDTO {
    private String userId;
    private String month;
    private double totalExpense;
    private double averageExpense;
    private double maxExpense;
    private double minExpense;

    public ExpenseSummaryDTO(String month, double total, double avg, double max, double min) {
        this.month = month;
        this.totalExpense = total;
        this.averageExpense = avg;
        this.maxExpense = max;
        this.minExpense = min;
    }

}
