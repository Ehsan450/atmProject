package com.example.demo.service;

import com.example.demo.entity.Transaction;
import com.example.demo.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    private Optional<Transaction> findTransaction(int id) {
        return transactionRepository.findById(id);
    }

    private List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

}
