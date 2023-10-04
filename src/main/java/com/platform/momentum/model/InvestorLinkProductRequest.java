package com.platform.momentum.model;

import com.platform.momentum.model.domain.Investor;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter @Setter
public class InvestorLinkProductRequest {
    private Investor investor;
}
