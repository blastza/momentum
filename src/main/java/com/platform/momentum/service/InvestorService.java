package com.platform.momentum.service;

import com.platform.momentum.entity.WithdrawalEntity;
import com.platform.momentum.exception.InvestorNotFoundException;
import com.platform.momentum.model.InvestorLinkProductRequest;
import com.platform.momentum.model.WithdrawalRequest;
import com.platform.momentum.model.WithdrawalResponse;
import com.platform.momentum.model.domain.Investor;
import com.platform.momentum.model.domain.Product;
import com.platform.momentum.model.domain.ProductType;
import com.platform.momentum.repository.InvestorRepository;
import com.platform.momentum.repository.ProductRepository;
import com.platform.momentum.repository.WithdrawalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvestorService {

    private final InvestorRepository investorRepository;
    private final ProductRepository productRepository;
    private final WithdrawalRepository withdrawalRepository;

    public Investor findInvestorByEmail(String email) {
        var investorDetails = investorRepository.findByEmail(email);
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

        WithdrawalResponse response = null;

        //select a product they are withdrawing from
        List<Product> products = byEmail.getProducts();
        //loop through to check if the product requested exist
        for (Product product: products){
            //if productType is equal to request productType
            if(request.getProductType().equals(product.getType())){
                //if productType is equal to Retirement, then validate
                if(product.getType().equals(ProductType.RETIREMENT)){
                    if((calculateAge(byEmail.getBirth_date(), LocalDate.now()))
                            && request.getWithdrawal_amount() < product.getCurrent_balance()
                            && (allowedPercentageToWithdraw(request, product)) ){
                        response = withdrawAndCaptureDetails(product,request);
                    }
                }else {
                    response = withdrawAndCaptureDetails(product,request);
                }
            }
        }
        return response;
    }

    public Investor findInvestorById(Integer investor_id) {
        var investor = investorRepository.findById(investor_id).get();

        if(investor.getEmail() == null)
            throw new InvestorNotFoundException("requested investor does not exist");
        return investor;
    }

    public boolean allowedPercentageToWithdraw(WithdrawalRequest request ,Product product){
        Double percentageAllowed = product.getCurrent_balance() * 0.9;

        if(request.getWithdrawal_amount() < percentageAllowed)
            return true;
        return false;
    }

    public boolean calculateAge(LocalDate dob, LocalDate current){
        Period diff = Period.between(dob, current);
        if(diff.getYears() > 65)
            return false;
        return true;
    }

    private WithdrawalResponse withdrawAndCaptureDetails(Product product, WithdrawalRequest request){
        Double newBalance = product.getCurrent_balance() - request.getWithdrawal_amount();
        WithdrawalResponse response = new WithdrawalResponse();

        response.setPrevious_balance(product.getCurrent_balance());
        product.setCurrent_balance(newBalance);

        productRepository.save(product);

        response.setAmount_withdrawn(request.getWithdrawal_amount());
        response.setType(request.getProductType());
        response.setName(product.getName());
        response.setCurrent_balance(newBalance);

        withdrawalRepository.save( new WithdrawalEntity().builder()
                .withdrawal_amount(request.getWithdrawal_amount())
                .accountNumber(request.getAccountNumber())
                .referenceName(request.getReferenceName())
                .build());

        return response;
    }
}
