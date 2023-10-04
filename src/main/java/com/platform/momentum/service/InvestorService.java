package com.platform.momentum.service;

import com.platform.momentum.entity.UserEntity;
import com.platform.momentum.entity.WithdrawalEntity;
import com.platform.momentum.exception.InvestorNotFoundException;
import com.platform.momentum.model.InvestorLinkProductRequest;
import com.platform.momentum.model.WithdrawalRequest;
import com.platform.momentum.model.WithdrawalResponse;
import com.platform.momentum.model.domain.Investor;
import com.platform.momentum.model.domain.Product;
import com.platform.momentum.repository.InvestorRepository;
import com.platform.momentum.repository.ProductRepository;
import com.platform.momentum.repository.WithdrawalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvestorService {

    private final InvestorRepository investorRepository;
    private final ProductRepository productRepository;
    private final WithdrawalRepository withdrawalRepository;


    public Investor findInvestorByEmail(String email) {
        Investor investorDetails = investorRepository.findByEmail(email);
        if(investorDetails == null)
            throw new InvestorNotFoundException("requested investor does not exist");
        return investorDetails;

    }

    public List<Investor> findAllInvestors() {
        List<Investor> investorList = investorRepository.findAll();
        if(investorList.isEmpty())
            throw new InvestorNotFoundException("No investors found");
        return investorList;
    }

    public Investor saveInvestor(InvestorLinkProductRequest request) {
        return investorRepository.save(request.getInvestor());
    }

    public WithdrawalResponse withdrawal(WithdrawalRequest request) {
        Investor byEmail = investorRepository.findByEmail(request.getEmail());

        WithdrawalResponse response = new WithdrawalResponse();

        //select a product they are withdrawing from
        List<Product> products = byEmail.getProducts();
        for (Product product: products){
            if(request.getName().equals(product.getName())
                    && request.getWithdrawal_amount() < product.getCurrent_balance()
                    && allowedPercentageToWithdraw(request.getWithdrawal_amount(), product.getCurrent_balance())
            ) {
                Double newBalance = product.getCurrent_balance() - request.getWithdrawal_amount();
                response.setPrevious_balance(product.getCurrent_balance());
                product.setCurrent_balance(newBalance);

                productRepository.save(product);

                response.setAmount_withdrawn(request.getWithdrawal_amount());
                response.setType(request.getProductType());
                response.setName(product.getName());
                response.setCurrent_balance(newBalance);


                withdrawalRepository.save(
                        new WithdrawalEntity().builder()
                                .accountNumber(request.getAccountNumber())
                                .referenceName(request.getReferenceName())
                                .withdrawal_amount(request.getWithdrawal_amount())
                                .build()
                );
            }
        }

        return response;
    }

    public boolean allowedPercentageToWithdraw(Double withdrawAmount ,Double currentBalance){
        Double percentageAllowed = currentBalance * 0.9;

        if(withdrawAmount < percentageAllowed)
            return true;
        return false;
    }

    public boolean calculateAge(LocalDate dob, LocalDate current){
        Period diff = Period.between(dob, current);
        if(diff.getYears() > 65)
            return true;
        return false;
    }

    public Investor findInvestorById(Integer investor_id) {
        Investor investor = investorRepository.findById(investor_id).get();
        System.out.println("Lutendo " + investor.getEmail());
        System.out.println("Lutendo " + investor.getFirstname());

        if(investor.getEmail() == null)
            throw new InvestorNotFoundException("requested investor does not exist");
        return investor;

    }
}
