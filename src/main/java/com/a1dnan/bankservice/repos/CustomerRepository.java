package com.a1dnan.bankservice.repos;

import com.a1dnan.bankservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
