package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Card;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, CardRepository cardRepository) {
        this.accountRepository = accountRepository;
        this.cardRepository = cardRepository;
    }

    public Optional<Account> findAccount(String id) {
        return accountRepository.findById(id);
    }

    public void deleteAccount(String id) {
        accountRepository.deleteById(id);
    }

    public Account saveAccount(Account account) {
        account.setAccountNumber(Utils.generate(14));
        Account savedAccount = accountRepository.save(account);
        Card card = new Card();
        card.setCardNo(Utils.generate(12));
        card.setPin(Utils.generate(4));
        card.setAccount(savedAccount);
        this.cardRepository.saveAndFlush(card);
        return savedAccount;
    }
}
