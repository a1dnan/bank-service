package com.a1dnan.bankservice.service;

import com.a1dnan.bankservice.dtos.BankAccountRequestDTO;
import com.a1dnan.bankservice.dtos.BankAccountResponseDTO;
import com.a1dnan.bankservice.entities.BankAccount;
import com.a1dnan.bankservice.mappers.AccountMapper;
import com.a1dnan.bankservice.repos.BankAccountRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final BankAccountRepository bankAccountRepository;


    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {

        BankAccount bankAccount = AccountMapper.toBankAccount(bankAccountDTO);
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreatedAt(new Date());
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        return AccountMapper.fromBankAccount(savedBankAccount);
    }

    @Override
    public List<BankAccountResponseDTO> findAllAccounts() {

        return bankAccountRepository.findAll()
                .stream()
                .map(AccountMapper::fromBankAccount).toList();
    }

    @Override
    public BankAccountResponseDTO findAccount(String id) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Account not found")
        );
        return AccountMapper.fromBankAccount(bankAccount);
    }

    @Override
    public BankAccountResponseDTO updateAccount(BankAccountRequestDTO bankAccountRequestDTO, String id) {
        BankAccount account = bankAccountRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Account not found")
        );
        BankAccount bankAccount = AccountMapper.toBankAccount(bankAccountRequestDTO);
        account.setType(bankAccount.getType());
        account.setCurrency(bankAccount.getCurrency());
        account.setBalance(bankAccount.getBalance());
        return AccountMapper.fromBankAccount(account);
    }

    @Override
    public void deleteAccount(String id) {
        bankAccountRepository.deleteById(id);
    }
}
