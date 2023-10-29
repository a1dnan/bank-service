package com.a1dnan.bankservice.web;

import com.a1dnan.bankservice.entities.BankAccount;
import com.a1dnan.bankservice.repos.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AccountGraphQLController {

    private final BankAccountRepository bankAccountRepository;

    @QueryMapping
    public List<BankAccount> accountsList(){
        return bankAccountRepository.findAll();
    }

    @QueryMapping
    public BankAccount accountById(@Argument String id){
        return bankAccountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(String.format("Account %s not found",id)));
    }
}
