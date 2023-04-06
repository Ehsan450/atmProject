package com.example.demo.entity;

import com.example.demo.enumerator.TransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "transaction_on")
    private LocalDateTime transactionOn;

    @Column(name = "trx_type")
    private TransactionType trxType;

    @ManyToOne
    @JoinColumn(name = "src_account", referencedColumnName = "account_no")
    private Account srcAccount;

    @ManyToOne
    @JoinColumn(name = "dest_account", referencedColumnName = "account_no")
    private Account destAccount;
}
