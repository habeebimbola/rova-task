package com.rovatask.repo;

import com.rovatask.domain.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Integer> {

    public abstract Optional< CustomerAccount> findByCustomerId(Integer customerId);
}
