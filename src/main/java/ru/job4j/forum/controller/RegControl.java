package ru.job4j.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegControl {

    @PostMapping("/reg")
    public String regSave(Model model) {
        model.addAttribute("errorMessage",
                "Регистрация пользователей будет реализована при подключении БД");
        return "reg";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}