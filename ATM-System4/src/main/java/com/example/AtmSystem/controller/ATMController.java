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

    @PostMapping("/user/login")
    public String login(User user, Model model) {
        if (null == bankAccountRepositroy.verification(user.cardNumber, user.getPassword()))
            return "user/login";
        else {
            user = bankAccountRepositroy.findByCardNumber(user.cardNumber);
            model.addAttribute("user", user);
            return "user/atm";
        }
    }

    @GetMapping("/user/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/user/login/{cardNumber}")
    public String login(@PathVariable("cardNumber") String cardNumber, Model model) {
        User user = bankAccountRepositroy.findByCardNumber(cardNumber);
        model.addAttribute("user", user);
        System.out.println(user.toString());
        return "user/login";
    }

    @GetMapping("/user/balance/{cardNumber}")
    public String balance(@PathVariable("cardNumber") String cardNumber, Model model) {
        System.out.println("balance");
        User user = bankAccountRepositroy.findByCardNumber(cardNumber);
        model.addAttribute("user", user);
        System.out.println(user.toString());
        return "user/balance";
    }

}
