package com.platform.momentum.service;

import com.platform.momentum.model.domain.Investor;
import com.platform.momentum.model.domain.Product;
import com.platform.momentum.repository.InvestorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService  {

    private final InvestorRepository investorRepository;

    public List<Product> findAllProductsLinkedToInvestor(String email) {
        Investor productByEmail = investorRepository.findByEmail(email);
        return productByEmail.getProducts();
    }
}
