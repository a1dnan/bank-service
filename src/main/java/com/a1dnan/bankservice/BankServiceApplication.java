package com.a1dnan.bankservice;

import com.a1dnan.bankservice.entities.BankAccount;
import com.a1dnan.bankservice.entities.Customer;
import com.a1dnan.bankservice.enums.AccountType;
import com.a1dnan.bankservice.repos.BankAccountRepository;
import com.a1dnan.bankservice.repos.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BankServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankServiceApplication.class, args);
	}


	@Bean
	CommandLineRunner start(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository){
		return args -> {
			Stream.of("Adnan", "Mohamed").forEach(c->{
				Customer customer = Customer.builder()
						.name(c)
						.build();
				customerRepository.save(customer);
			});

			customerRepository.findAll().forEach(customer -> {
				for(int i = 0 ; i<2 ;i++){
					BankAccount bankAccount = BankAccount.builder()
							.id(UUID.randomUUID().toString())
							.type(Math.random()>0.5?AccountType.CURRENT_ACCOUNT:AccountType.SAVING_ACCOUNT)
							.balance(10000*Math.random())
							.createdAt(new Date())
							.currency("MAD")
							.customer(customer)
							.build();
					bankAccountRepository.save(bankAccount);
				}
			});
		};
	}
}
