package com.spendora.notification_service.repo;

import com.spendora.notification_service.dto.UserBudgetDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBudgetRepository extends JpaRepository<UserBudgetDTO,Long> {
}
