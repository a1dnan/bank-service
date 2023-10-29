package com.a1dnan.bankservice.service;

import com.a1dnan.bankservice.dtos.BankAccountRequestDTO;
import com.a1dnan.bankservice.dtos.BankAccountResponseDTO;

import java.util.List;

public interface AccountService {

    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);

    public List<BankAccountResponseDTO> findAllAccounts();

    public BankAccountResponseDTO findAccount(String id);

    public BankAccountResponseDTO updateAccount(BankAccountRequestDTO bankAccountRequestDTO, String id);

    public void deleteAccount(String id);
}
