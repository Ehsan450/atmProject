package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Card;
import com.example.demo.entity.Transaction;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CardRepository;
import com.example.demo.repository.TransactionRepository;

import java.util.Optional;

public class AtmService {
    private AccountRepository accountRepository;
    private CardRepository cardRepository;
    private TransactionRepository transactionRepository;

    public boolean withdraw(String cardNo, String pin, double amount) {
        Optional<Card> card = cardRepository.findById(cardNo);

        if (card.isPresent()) {
            Card fetchedCard = card.get();
            Account account = fetchedCard.getAccount();
            if (amount > 0 && amount % 500 == 0) {
                if (amount <= account.getBalance()) {
                    account.setBalance(account.getBalance() - amount);
                    accountRepository.saveAndFlush(account);
                    System.out.println("Withdrawn: " + amount);
                    Transaction transaction;
                    

                    return true;
                }
                System.err.println("Insufficient Balance");
                return false;
            }
            System.err.println("Amount must be in multiples of 500");
            return false;
        }
        System.out.println("Card not found");
        return false;
    }


}
