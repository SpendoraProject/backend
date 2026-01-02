package com.spendora.analytics_service.repo;

import com.spendora.analytics_service.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseAnalyticsRepository extends JpaRepository<ExpenseEntity, Long> {

    @Query("""
        SELECT SUM(e.amount)
        FROM ExpenseEntity e
        WHERE e.userId = :userId
          AND e.expenseDate BETWEEN :start AND :end
    """)
    BigDecimal getTotalExpenseForPeriod(
            String userId,
            LocalDate start,
            LocalDate end
    );

    @Query("""
        SELECT e.category, SUM(e.amount)
        FROM ExpenseEntity e
        WHERE e.userId = :userId
        GROUP BY e.category
    """)
    List<Object[]> getCategoryWiseExpense(String userId);

    @Query("""
        SELECT e.expenseDate, SUM(e.amount)
        FROM ExpenseEntity e
        WHERE e.userId = :userId
        GROUP BY e.expenseDate
        ORDER BY e.expenseDate
    """)
    List<Object[]> getDailyExpenseTrend(String userId);
}
