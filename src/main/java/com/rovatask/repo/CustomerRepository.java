package com.rovatask.repo;

import com.rovatask.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    public abstract Optional<Customer> findByCustomerId(Integer customerId);
}
