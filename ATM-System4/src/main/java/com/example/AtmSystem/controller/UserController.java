package com.example.AtmSystem.controller;

import com.example.AtmSystem.model.User;
import com.example.AtmSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/main")
    public String getMainPage() {
        return "main-page";
    }

    @RequestMapping(value = "/list")
    public String getListOfAccounts(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "list";
    }

    @GetMapping("/admin/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin/user-list";
    }

    @GetMapping("/admin/user-create")
    public String createUserForm(User user) {
        return "admin/user-create";
    }

    @PostMapping("/admin/user-create")
    public String createUser(User user) {
        System.out.println(user.toString());
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("admin/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "admin/user-update";
    }

    @PostMapping("/admin/user-update")
    public String updateUser(User user) {
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/user/withdrawMoney/{id}")
    public String withdrawMoneyForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user/withdrawMoney";
    }

    @PostMapping("/user/withdrawMoney")
    public String withdrawMoney(User user) {
        int moneySum = user.getMoney();
        user = userService.findById(user.getId());
        user.setMoney(user.getMoney() - moneySum);
        userService.saveUser(user);
        return "user/login";
    }

    @GetMapping("/user/topUpAccount/{id}")
    public String topUpAccountForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user/topUpAccount";
    }

    @PostMapping("/user/topUpAccount")
    public String topUpAccount(User user) {
        int moneySum = user.getMoney();
        user = userService.findById(user.getId());
        user.setMoney(user.getMoney() + moneySum);
        userService.saveUser(user);
        return "user/login";
    }

    @GetMapping("/user/changePin/{id}")
    public String changePinForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user/changePin";
    }

    @PostMapping("/user/changePin")
    public String changePin(User user) {
        String moneySum = user.getPassword();
        user = userService.findById(user.getId());
        user.setPassword(moneySum);
        userService.saveUser(user);
        return "user/login";
    }
}
