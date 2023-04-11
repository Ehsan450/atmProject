package com.example.demo.controller;

import com.example.demo.entity.Card;
import com.example.demo.service.AtmService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class AtmController {

    private final AtmService atmService;

//    @Autowired
//    public AtmController(AtmService atmService) {
//        this.atmService = atmService;
//    }

    @GetMapping("/")
    public ModelAndView atmForm(Model model) {
        model.addAttribute("card", new Card());

        return new ModelAndView("ATMForm");
    }

    @GetMapping("/mainpage")
    public ModelAndView Home() {
        return new ModelAndView("MainPage");
    }

    @PostMapping("/validate")
    public ModelAndView validate(@ModelAttribute Card card, Model model) {
        System.out.println(card);
//        boolean authenticated = this.atmService.validation(card.getCardNo(), card.getPin());
//
//        if (authenticated) {
//            return new ModelAndView(new RedirectView("/mainpage"));
//        }
//
//        model.addAttribute("message", "Incorrect Card/Pin");
        return new ModelAndView("ATMForm");
    }

    @PostMapping("/withdraw")
    public ModelAndView withdrawBalance(@ModelAttribute Card card) {
        atmService.withdraw(card.getCardNo(), card.getPin(),card.getAccount().getBalance());
return null;
    }

    @PostMapping("/deposit")
    public void depositBalance(String cardNo, String pin, double amount) {
        atmService.deposit(cardNo, pin, amount);

    }

    @PostMapping("/transfer")

    public void transferBalance(String cardNo, String pin, String destAccount, double amount) {
        atmService.transfer(cardNo, pin, destAccount, amount);

    }

    @PostMapping("/balance")
    public ModelAndView checkBalance(@ModelAttribute Card card, Model model) {
        double bal = atmService.checkBalance(card.getCardNo(), card.getPin());

        model.addAttribute("balance", bal);

        return new ModelAndView("balance");
    }

}
