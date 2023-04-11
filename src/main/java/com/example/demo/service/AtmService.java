package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Card;
import com.example.demo.entity.Transaction;
import com.example.demo.enumerator.TransactionType;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CardRepository;
import com.example.demo.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
@Service

public class AtmService {
    private AccountRepository accountRepository;
    private CardRepository cardRepository;
    private TransactionRepository transactionRepository;

    public boolean validation(String cardNo , String pin) {
        Optional<Card> card = cardRepository.findById(cardNo);
        if (card.isPresent()) {
            Card fetchedCard = card.get();
            if (fetchedCard.getPin().equals(pin)) {
                return true;
            }
            throw new RuntimeException("PIN Incorrect");

        }
        throw new RuntimeException("Card Not Found");
    }
    public void withdraw(String cardNo, String pin, double amount) {
        Optional<Card> card = cardRepository.findById(cardNo);

        if (card.isPresent()) {
            Card fetchedCard = card.get();
            if (fetchedCard.getPin().equals(pin)) {
                Account account = fetchedCard.getAccount();

                if (amount > 0 && amount % 500 == 0) {
                    if (amount <= account.getBalance()) {
                        account.setBalance(account.getBalance() - amount);
                        accountRepository.saveAndFlush(account);
                        System.out.println("Withdrawn: " + amount);
                        Transaction transaction = new Transaction();
                        transaction.setTrxType(TransactionType.WITHDRAW);
                        transaction.setSrcAccount(account);
                        transaction.setDestAccount(account);
                        transaction.setTransactionOn(LocalDateTime.now());
                        transactionRepository.saveAndFlush(transaction);


                    }
                    throw new RuntimeException("Insufficient Balance");


                }
                throw new RuntimeException("Amount must be in multiples of 500");

            }

            throw new RuntimeException("PIN Incorrect");

        }
        throw new RuntimeException("Card not found");


    }

    public void deposit(String cardNo, String pin, double amount) {
        Optional<Card> card = cardRepository.findById(cardNo);
        if (card.isPresent()) {
            Card fetchedCard = card.get();
            if (fetchedCard.getPin().equals(pin)) {

                Account account = fetchedCard.getAccount();
                if (amount > 0) {

                    account.setBalance(account.getBalance() + amount);
                    accountRepository.saveAndFlush(account);
                    System.out.println("Deposited Successfully : " + amount);
                    Transaction transaction = new Transaction();
                    transaction.setTrxType(TransactionType.DEPOSIT);
                    transaction.setSrcAccount(account);
                    transaction.setDestAccount(account);
                    transaction.setTransactionOn(LocalDateTime.now());
                    transactionRepository.saveAndFlush(transaction);
                }
                System.out.println("Input amount wrong");


            }
            System.out.println("Wrong Pin");

        }
        System.out.println("Card not Found");
    }

    public double checkBalance(String cardNo, String pin) {
        Optional<Card> card = cardRepository.findById(cardNo);
        if (card.isPresent()) {
            Card fetchedCard = card.get();
            if (fetchedCard.getPin().equals(pin)) {
                Account account = fetchedCard.getAccount();
                return account.getBalance();

            }
            throw new RuntimeException("Pin Incorrect");
        }
        throw new RuntimeException("Card not found");
    }

    public void transfer(String cardNo, String pin, String destAccount, double amount) {
        Optional<Card> card = cardRepository.findById(cardNo);
        if (card.isPresent()) {
            Card fetchedCard = card.get();
            if (fetchedCard.getPin().equals(pin)) {
                Account account = fetchedCard.getAccount();
                if (amount > 0 && account.getBalance() > amount) {
                    Optional<Account> destinationAccount = accountRepository.findById(destAccount);
                    if (destinationAccount.isPresent()) {
                        Account fetchedAccount = destinationAccount.get();

                        account.setBalance(account.getBalance() - amount);
                        accountRepository.saveAndFlush(account);
                        fetchedAccount.setBalance(fetchedAccount.getBalance() + amount);
                        accountRepository.saveAndFlush(fetchedAccount);
                        System.out.println("Balance Transfer Complete!!!" + amount + "to :" + fetchedAccount.getAccountNumber());
                        Transaction transaction = new Transaction();
                        transaction.setTrxType(TransactionType.TRANSFER);
                        transaction.setSrcAccount(account);
                        transaction.setDestAccount(fetchedAccount);
                        transaction.setTransactionOn(LocalDateTime.now());
                        transactionRepository.saveAndFlush(transaction);

                    }
                    throw new RuntimeException("Invalid Destination Account");


                }
                throw new RuntimeException("Amount Cant be negative");

            }
            throw new RuntimeException("Incorrect PIN");

        }
        throw new RuntimeException("Card Not Found");
    }

}
