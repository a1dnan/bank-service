package com.a1dnan.bankservice.web;

import com.a1dnan.bankservice.dtos.BankAccountRequestDTO;
import com.a1dnan.bankservice.dtos.BankAccountResponseDTO;
import com.a1dnan.bankservice.entities.BankAccount;
import com.a1dnan.bankservice.repos.BankAccountRepository;
import com.a1dnan.bankservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AccountRestController {

    private final BankAccountRepository bankAccountRepository;
    private final AccountService accountService;

    @GetMapping("/bankAccounts")
    public List<BankAccountResponseDTO> bankAccounts(){
        return accountService.findAllAccounts();
    }
    @GetMapping("/bankAccounts/{id}")
    public BankAccountResponseDTO bankAccount(@PathVariable String id){
        return accountService.findAccount(id);
    }

    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO bankAccount(@RequestBody BankAccountRequestDTO bankAccount){
        return accountService.addAccount(bankAccount);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccountResponseDTO update(@RequestBody BankAccountRequestDTO bankAccount, @PathVariable String id){

        return accountService.updateAccount(bankAccount, id);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id){
        bankAccountRepository.deleteById(id);
    }
}
