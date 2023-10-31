package com.a1dnan.bankservice.web;

import com.a1dnan.bankservice.dtos.BankAccountRequestDTO;
import com.a1dnan.bankservice.dtos.BankAccountResponseDTO;
import com.a1dnan.bankservice.entities.BankAccount;
import com.a1dnan.bankservice.entities.Customer;
import com.a1dnan.bankservice.repos.BankAccountRepository;
import com.a1dnan.bankservice.repos.CustomerRepository;
import com.a1dnan.bankservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AccountGraphQLController {

    private final BankAccountRepository bankAccountRepository;
    private final AccountService accountService;
    private final CustomerRepository customerRepository;

    @QueryMapping
    public List<BankAccount> accountsList(){
        return bankAccountRepository.findAll();
    }

    @QueryMapping
    public BankAccount accountById(@Argument String id){
        return bankAccountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(String.format("Account %s not found",id)));
    }

    @MutationMapping
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO bankAccount){
        return accountService.addAccount(bankAccount);
    }

    @MutationMapping
    public BankAccountResponseDTO updateAccount(@Argument String id, @Argument BankAccountRequestDTO bankAccount){
        return accountService.updateAccount(bankAccount,id);
    }

    @MutationMapping
    public void deleteAccount(@Argument String id){
        accountService.deleteAccount(id);
    }

    @QueryMapping
    public List<Customer> customers(){
        return customerRepository.findAll();
    }

}
