package com.example.AtmSystem.controller;

import com.example.AtmSystem.model.User;
import com.example.AtmSystem.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ATMController {
    private final BankAccountRepository bankAccountRepositroy;

    @Autowired
    public ATMController(BankAccountRepository bankAccountRepositroy) {
        this.bankAccountRepositroy = bankAccountRepositroy;
    }

    @GetMapping("/atm")
    public String findOneUser(@RequestParam(name = "name") String cardNumber, Model model) {
        System.out.println(cardNumber);
        User user = bankAccountRepositroy.findByCardNumber(cardNumber);
        System.out.println(user);
        model.addAttribute("user", user);
        return "user/atm";
    }


}
