package com.platform.momentum.model;

import com.platform.momentum.model.domain.ProductType;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class WithdrawalResponse {
    private ProductType type;
    private String name;
    private Double current_balance;
    private Double previous_balance;
    private Double amount_withdrawn;
}
