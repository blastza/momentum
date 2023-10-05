package com.platform.momentum.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "withdraw")
public class WithdrawalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double withdrawal_amount;
    private String accountNumber;
    private String referenceName;
/*    @ManyToOne
    private Investor investor;*/
}
