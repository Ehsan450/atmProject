package com.example.demo.entity;

import com.example.demo.service.AtmService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @Column(name = "account_no")
    private String accountNumber;

    @Column(name= "account_name")
    private String accountName;

    @Column(name = "balance")
    private double balance;
}
