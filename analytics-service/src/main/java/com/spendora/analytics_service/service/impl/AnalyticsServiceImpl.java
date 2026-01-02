package com.spendora.analytics_service.service.impl;

import com.spendora.analytics_service.dto.ExpenseSummaryDTO;
import com.spendora.analytics_service.entity.ExpenseEntity;
import com.spendora.analytics_service.repo.ExpenseRepository;
import com.spendora.analytics_service.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public List<ExpenseSummaryDTO> getMonthlySummary() {
        List<ExpenseEntity> expenses = expenseRepository.findAll();

        Map<Integer, List<ExpenseEntity>> monthMap = expenses.stream()
                .collect(Collectors.groupingBy(exp -> exp.getExpenseDate().getMonthValue()));

        List<ExpenseSummaryDTO> summaryList = new ArrayList<>();

        for (Map.Entry<Integer, List<ExpenseEntity>> entry : monthMap.entrySet()) {
            int monthNum = entry.getKey();
            List<ExpenseEntity> monthExpenses = entry.getValue();

            double total = monthExpenses.stream()
                    .map(ExpenseEntity::getAmount)         // Stream<BigDecimal>
                    .mapToDouble(BigDecimal::doubleValue)  // convert to double
                    .sum();

            double avg = monthExpenses.stream()
                    .map(ExpenseEntity::getAmount)
                    .mapToDouble(BigDecimal::doubleValue)
                    .average()
                    .orElse(0);

            double max = monthExpenses.stream()
                    .map(ExpenseEntity::getAmount)
                    .mapToDouble(BigDecimal::doubleValue)
                    .max()
                    .orElse(0);

            double min = monthExpenses.stream()
                    .map(ExpenseEntity::getAmount)
                    .mapToDouble(BigDecimal::doubleValue)
                    .min()
                    .orElse(0);

            Month monthEnum = Month.of(monthNum);
            String monthName = monthEnum.getDisplayName(TextStyle.FULL, Locale.ENGLISH);

            summaryList.add(new ExpenseSummaryDTO(monthName, total, avg, max, min));
        }

        // Optional: Sort by month
        summaryList.sort(Comparator.comparing(dto -> Month.valueOf(dto.getMonth().toUpperCase()).getValue()));

        return summaryList;
    }
}
