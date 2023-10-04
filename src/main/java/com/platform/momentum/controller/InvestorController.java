package com.platform.momentum.controller;

import com.platform.momentum.model.InvestorLinkProductRequest;
import com.platform.momentum.model.WithdrawalRequest;
import com.platform.momentum.model.WithdrawalResponse;
import com.platform.momentum.model.domain.Investor;
import com.platform.momentum.model.domain.Product;
import com.platform.momentum.security.UserPrincipal;
import com.platform.momentum.service.InvestorService;
import com.platform.momentum.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/investment")
@RequiredArgsConstructor
public class InvestorController {

    private final InvestorService investorService;
    private final ProductService productService;

    //service for retrieving back investor details using email
    @GetMapping("/investor-details/{email}")
    public ResponseEntity<Investor> retrieveInvestorDetailsByEmail(@AuthenticationPrincipal UserPrincipal principal,
                                                                               @PathVariable String email){
        return ResponseEntity.ok(investorService.findInvestorByEmail(email));
    }

    //service for retrieving back investor details using email
    @GetMapping("/investor-details/{investor_id}/byId")
    public ResponseEntity<Investor> retrieveInvestorDetailsById(@AuthenticationPrincipal UserPrincipal principal,
                                                                   @PathVariable Integer investor_id){
        return ResponseEntity.ok(investorService.findInvestorById(investor_id));
    }

    //service for retrieving back all investor details
    @GetMapping("/investor-details")
    public ResponseEntity<List<Investor>> retrieveAllInvestorsDetails(){
        return ResponseEntity.ok(investorService.findAllInvestors());
    }

    @PostMapping("/link-product")
    public ResponseEntity<Investor> linkProduct(@RequestBody InvestorLinkProductRequest request){
        return ResponseEntity.ok(investorService.saveInvestor(request));
    }

    //Get product byEmail
    @GetMapping("/investor/products/{email}")
    public ResponseEntity<List<Product>> retrieveAllInvestorsProducts(@PathVariable String email){
        return ResponseEntity.ok(productService.findAllProductsLinkedToInvestor(email));
    }

    @PutMapping("/investor/withdraw")
    public ResponseEntity<WithdrawalResponse> withdraw(@RequestBody WithdrawalRequest request){
        return ResponseEntity.ok(investorService.withdrawal(request));
    }
}
