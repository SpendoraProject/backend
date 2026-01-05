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
    public List<ExpenseSummaryDTO> getMonthlySummary(String userId) {

        List<ExpenseEntity> expenses =
                expenseRepository.findByUserId(userId);

        Map<Integer, List<ExpenseEntity>> monthMap =
                expenses.stream()
                        .collect(Collectors.groupingBy(
                                exp -> exp.getExpenseDate().getMonthValue()
                        ));

        List<ExpenseSummaryDTO> summaryList = new ArrayList<>();

        for (Map.Entry<Integer, List<ExpenseEntity>> entry : monthMap.entrySet()) {

            int monthNum = entry.getKey();
            List<ExpenseEntity> monthExpenses = entry.getValue();

            DoubleSummaryStatistics stats =
                    monthExpenses.stream()
                            .map(ExpenseEntity::getAmount)
                            .mapToDouble(BigDecimal::doubleValue)
                            .summaryStatistics();

            Month monthEnum = Month.of(monthNum);
            String monthName =
                    monthEnum.getDisplayName(TextStyle.FULL, Locale.ENGLISH);

            summaryList.add(new ExpenseSummaryDTO(
                    monthName,
                    stats.getSum(),
                    stats.getAverage(),
                    stats.getMax(),
                    stats.getMin()
            ));
        }

        summaryList.sort(
                Comparator.comparing(
                        dto -> Month.valueOf(dto.getMonth().toUpperCase()).getValue()
                )
        );

        return summaryList;
    }

}
