package ru.job4j.forum.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.ForumService;

@Controller
public class PostControl {

    private final ForumService service;

    public PostControl(ForumService service) {
        this.service = service;
    }

    @GetMapping("/topic")
    public String showTopic(@RequestParam("id") int id, Model model) {
        model.addAttribute("user",
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("topic", service.getTopicById(id));
        return "topic";
    }

    @PostMapping("/savePost")
    public String save(@ModelAttribute Post post,
                       @RequestParam("topicId") int topicId) {
        if (post.getId() == 0) {
            UserDetails loggedUser = (UserDetails) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
            post.setAuthor(service.getUserByUserName(loggedUser.getUsername()));
        } else {
            post.setAuthor(service.getPostByTopicIdAndPostId(topicId, post.getId()).getAuthor());
        }
        service.savePost(topicId, post);
        return "redirect:/topic?id=" + topicId;
    }

    @GetMapping("/updatePost")
    public String update(@RequestParam("tid") int topicId,
                         @RequestParam("pid") int postId,
                         Model model) {
        model.addAttribute("post", service.getPostByTopicIdAndPostId(topicId, postId));
        model.addAttribute("topicId", topicId);
        model.addAttribute("user",
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "updatePost";
    }

    @GetMapping("/deletePost")
    public String delete(@RequestParam("tid") int topicId,
                         @RequestParam("pid") int postId) {
        service.deletePostByTopicIdAndPostId(topicId, postId);
        return "redirect:/topic?id=" + topicId;
    }

}