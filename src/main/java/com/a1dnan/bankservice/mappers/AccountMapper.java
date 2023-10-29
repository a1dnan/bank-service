package com.a1dnan.bankservice.mappers;

import com.a1dnan.bankservice.dtos.BankAccountRequestDTO;
import com.a1dnan.bankservice.dtos.BankAccountResponseDTO;
import com.a1dnan.bankservice.entities.BankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public static BankAccountResponseDTO fromBankAccount(BankAccount bankAccount){
        BankAccountResponseDTO bankAccountResponseDTO = new BankAccountResponseDTO();
        BeanUtils.copyProperties(bankAccount, bankAccountResponseDTO);

        return bankAccountResponseDTO;
    }

    public static BankAccount toBankAccount(BankAccountRequestDTO bankAccountRequestDTO){
        BankAccount bankAccount = new BankAccount();
        BeanUtils.copyProperties(bankAccountRequestDTO, bankAccount);

        return bankAccount;
    }

}
