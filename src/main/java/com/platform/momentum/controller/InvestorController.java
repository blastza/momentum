package com.platform.momentum.controller;

import com.platform.momentum.exception.UnauthorizedInvestorException;
import com.platform.momentum.model.InvestorLinkProductRequest;
import com.platform.momentum.model.WithdrawalRequest;
import com.platform.momentum.model.WithdrawalResponse;
import com.platform.momentum.model.domain.Investor;
import com.platform.momentum.model.domain.Product;
import com.platform.momentum.security.UserPrincipal;
import com.platform.momentum.service.InvestorService;
import com.platform.momentum.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "retrieve investor details by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "403", description = "Unauthorized - no token available"),
            @ApiResponse(responseCode = "400", description = "Bad request - something is wrong with the request")
    })
    @GetMapping("/investor/get-by-email/{email}")
    public ResponseEntity<Investor> retrieveInvestorDetailsByEmail(@AuthenticationPrincipal UserPrincipal principal,
                                                                               @PathVariable String email){
        if(!(principal.getEmail().equals(email)))
            throw new UnauthorizedInvestorException("Not Authorised, email don't match with security");
        return ResponseEntity.ok(investorService.findInvestorByEmail(email));
    }

    @Operation(summary = "retrieve investor details by investor_id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "403", description = "Unauthorized - no token available"),
            @ApiResponse(responseCode = "400", description = "Bad request - something is wrong with the request")
    })
    @GetMapping("/investor/get-by-id/{investor_id}")
    public ResponseEntity<Investor> retrieveInvestorDetailsById(@AuthenticationPrincipal UserPrincipal principal,
                                                                   @PathVariable Integer investor_id){
        return ResponseEntity.ok(investorService.findInvestorById(investor_id));
    }

    @Operation(summary = "retrieve all investor details in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "403", description = "Unauthorized - no token available"),
            @ApiResponse(responseCode = "400", description = "Bad request - something is wrong with the request")
    })
    @GetMapping("/investor/all")
    public ResponseEntity<List<Investor>> retrieveAllInvestorsDetails(){
        return ResponseEntity.ok(investorService.findAllInvestors());
    }

    @Operation(summary = "Add products to an investor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "403", description = "Unauthorized - no token available"),
            @ApiResponse(responseCode = "400", description = "Bad request - something is wrong with the request")
    })
    @PostMapping("/link-product")
    public ResponseEntity<Investor> linkProduct(@RequestBody InvestorLinkProductRequest request){
        return ResponseEntity.ok(investorService.saveInvestor(request));
    }

    @Operation(summary = "retrieve products linked to an investor by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "403", description = "Unauthorized - no token available"),
            @ApiResponse(responseCode = "400", description = "Bad request - something is wrong with the request")
    })
    @GetMapping("/investor/products/{email}")
    public ResponseEntity<List<Product>> retrieveAllInvestorsProducts(@PathVariable String email){
        return ResponseEntity.ok(productService.findAllProductsLinkedToInvestor(email));
    }

    @Operation(summary = "withdraw from a given product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "403", description = "Unauthorized - no token available"),
            @ApiResponse(responseCode = "400", description = "Bad request - something is wrong with the request")
    })
    @PutMapping("/investor/withdraw")
    public ResponseEntity<WithdrawalResponse> withdraw(@RequestBody WithdrawalRequest request){
        return ResponseEntity.ok(investorService.withdrawal(request));
    }
}
