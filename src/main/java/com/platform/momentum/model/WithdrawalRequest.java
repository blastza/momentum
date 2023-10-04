package com.platform.momentum.model;

import com.platform.momentum.model.domain.ProductType;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class WithdrawalRequest {

    private String name;
    private String email;
    private String accountNumber;
    private String referenceName;
    private Double withdrawal_amount;
    private ProductType productType;

}
