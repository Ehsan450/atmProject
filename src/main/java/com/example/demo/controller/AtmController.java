package com.example.demo.controller;

import com.example.demo.entity.Card;
import com.example.demo.service.AtmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AtmController {
    public AtmService atmService;

    @Autowired
    public AtmController(AtmService atmService) {
        this.atmService = atmService;
    }

    @GetMapping
    public ModelAndView atmForm(Model model) {
        model.addAttribute("card", new Card());

        return new ModelAndView("ATMForm");
    }
    @GetMapping("/mainpage")
    public ModelAndView Home(){
        return new ModelAndView("MainPage");
    }

    @PostMapping("/")
    public ModelAndView validate(@ModelAttribute Card card, Model model){
        boolean authenticated = this.atmService.validation(card.getCardNo(), card.getPin());

        if (authenticated) {
            return new ModelAndView(new RedirectView("/mainpage"));
        }

        model.addAttribute("message", "Incorrect Card/Pin");
        return new ModelAndView("ATMForm");
    }

}
