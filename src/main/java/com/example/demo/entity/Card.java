package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cards")
public class Card {
    @Id
    @Column(name = "card_no")
    private String cardNo;

    @Column(name = "pin")
    private String pin;

    @ManyToOne
    @JoinColumn(name = "account_no")
    private Account account_no;
}
