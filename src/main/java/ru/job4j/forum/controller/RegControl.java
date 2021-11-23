package ru.job4j.forum.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.ForumService;

@Controller
public class RegControl {

    private final PasswordEncoder encoder;
    private final ForumService service;

    public RegControl(PasswordEncoder encoder, ForumService service) {
        this.encoder = encoder;
        this.service = service;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, Model model) {
        if (service.getUserByUserName(user.getUsername()) != null) {
            model.addAttribute("errorMessage",
                    "Пользователь с таким именем уже зарегистрирован");
            return "reg";
        }
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(service.findAuthorityByName("ROLE_USER"));
        service.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}