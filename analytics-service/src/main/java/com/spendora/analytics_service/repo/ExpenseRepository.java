package com.spendora.analytics_service.repo;
import com.spendora.analytics_service.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {
    List<ExpenseEntity> findAll();
    List<ExpenseEntity> findByUserId(String userId);
}
