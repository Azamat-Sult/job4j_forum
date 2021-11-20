package ru.job4j.forum.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Topic;
import ru.job4j.forum.service.ForumService;

@Controller
public class TopicControl {

    private final ForumService service;

    public TopicControl(ForumService service) {
        this.service = service;
    }

    @GetMapping("/createTopic")
    public String create(Model model) {
        model.addAttribute("user",
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "createTopic";
    }

    @PostMapping("/saveTopic")
    public String save(@ModelAttribute Topic topic) {
        if (topic.getId() == 0) {
            UserDetails loggedUser = (UserDetails) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
            topic.setAuthor(service.getUserByUserName(loggedUser.getUsername()));
        } else {
            topic.setAuthor(service.getTopicById(topic.getId()).getAuthor());
        }
        service.saveTopic(topic);
        return "redirect:/";
    }

    @GetMapping("/updateTopic")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("topic", service.getTopicById(id));
        model.addAttribute("user",
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "updateTopic";
    }

    @GetMapping("/deleteTopic")
    public String delete(@RequestParam("id") int id) {
        service.deleteTopicById(id);
        return "redirect:/";
    }
}