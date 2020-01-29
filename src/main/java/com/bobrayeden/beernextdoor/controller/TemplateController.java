package com.bobrayeden.beernextdoor.controller;

import com.bobrayeden.beernextdoor.entity.User;
import com.bobrayeden.beernextdoor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class TemplateController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String toLog() {
        return "login";
    }

    @GetMapping("/sign-up")
    public String toSign(Model out,
                         @ModelAttribute User user) {
        out.addAttribute(user);
        return "signup";
    }

    @GetMapping("/main-page")
    public String toMain() {
        return "main";
    }

    @PostMapping("/connexion")
    public String connexion(@RequestParam String nameUser,
                            @RequestParam String password,
                            HttpSession session) {
        if (userRepository.findByNameUserAndPassword(nameUser, password).isPresent()) {
            User user = userRepository.findByNameUserAndPassword(nameUser, password).get();
            session.setAttribute("user", user);
            return "redirect:/main-page";
        } else if (userRepository.findByEmailAndPassword(nameUser, password).isPresent()) {
            User user = userRepository.findByEmailAndPassword(nameUser, password).get();
            session.setAttribute("user", user);
            return "redirect:/main-page";
        }
        return "login";
    }

    @PostMapping("/sign-in")
    public String signIn(Model out,
                         HttpSession session,
                         @RequestParam String confirmPass,
                         @ModelAttribute User user) {
        out.addAttribute("user", user);
        out.addAttribute("confirmPass", confirmPass);
        if (user.getNameUser() != null) {
            if (userRepository.findByNameUser(user.getNameUser()).isPresent()) {
                return "redirect:/sign-up";
            }
            if (user.getPassword().equals(confirmPass)) {
                userRepository.saveAndFlush(user);
                out.addAttribute("user", user);
                session.setAttribute("user", user);
                return "redirect:/main-page";
            }
        }
        return "redirect:/sign-up";
    }

    @GetMapping("/user-profile")
    public String toProfile() {
        return "profile";
    }

    @GetMapping("/logout")
    public String logOut() {
        return "redirect:/";
    }
}
