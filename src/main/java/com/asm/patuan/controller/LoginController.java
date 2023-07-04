package com.asm.patuan.controller;

import com.asm.patuan.request.CreateAccount;
import com.asm.patuan.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {


    @Autowired
    private UserService userService;

    @Autowired
    HttpSession session;

    @GetMapping("/login")
    public String login() {
        return "hi";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("register", new CreateAccount());
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("register") CreateAccount createAccount,
                           BindingResult result, Model model) {
        if (!createAccount.getPassword().equals(createAccount.getConfirmPassword())) {
            model.addAttribute("errorPass", "Mật khẩu không trùng nhau");
            return "register";
        } else {
            if (result.hasErrors()) {
                return "register";
            } else {
                userService.createAccount(createAccount);
                return "redirect:/login";
            }
        }
    }

}
