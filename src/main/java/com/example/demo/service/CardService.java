package com.example.demo.service;

import com.example.demo.entity.Card;
import com.example.demo.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CardService {
    public CardRepository cardRepository;

   public Optional<Card> findCard(String cardNo){
       return cardRepository.findById(cardNo);

   }
   public List<Card> findAllCards(){
      return cardRepository.findAll();
   }
   public List<Card> cardAllottedAccount(String accountNo){
       return cardRepository.findAllByAccountAccountNumber(accountNo);
   }


}
