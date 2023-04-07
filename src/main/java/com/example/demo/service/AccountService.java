package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    
    private AccountRepository accountRepository;
    @Autowired
    public AccountService (AccountRepository accountRepository){
        this.accountRepository=accountRepository;
    }
    public void findAccount(String id){
        accountRepository.findById(id);
    }
    public void deleteAccount(String id){
        accountRepository.deleteById(id);
    }
    public void saveAccount(Account account){
        accountRepository.save(account);
    }
    
}
