package com.rovatask.repo;

import com.rovatask.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    public abstract Optional<Account> findByAccountNumber(Integer accountNumber);
}
