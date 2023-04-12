package com.example.demo.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Actions {
    private String cardNo;

    private String pin;

    private double amount;

    private String destAccount;
}
