package com.rovatask.service;

import com.rovatask.domain.dto.AccountDto;

import java.util.Optional;

public interface AccountService{

    public abstract AccountDto createNewCurrentAccount(AccountDto accountDto);

    public abstract Optional<AccountDto> findAccountById(Integer accountId);
}
