package com.spendora.notification_service.dto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_budget")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBudgetDTO {

    @Id
    private String userId;

    private double monthlyLimit;
}
