package com.rovatask.service;

import com.rovatask.domain.dto.AccountDto;

public interface AccountService{

    public abstract AccountDto createNewCurrentAccount(AccountDto accountDto);
}
