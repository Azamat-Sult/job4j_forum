package ru.job4j.forum.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.forum.Main;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@Transactional
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ForumGetMethodTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void getIndexPageTest() throws Exception {
        this.mockMvc.perform(get("/index"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    @WithMockUser
    public void getLoginPageTest() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    @WithMockUser
    public void getRegPageTest() throws Exception {
        this.mockMvc.perform(get("/reg"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("reg"));
    }

    @Test
    @WithMockUser
    public void getShowTopicPageTest() throws Exception {
        this.mockMvc.perform(get("/topic?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("topic"));
    }

    @Test
    @WithMockUser(roles = {"MODERATOR"})
    public void getUpdatePostPageTest() throws Exception {
        this.mockMvc.perform(get("/updatePost?tid=1&pid=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("updatePost"));
    }

    @Test
    @WithMockUser(roles = {"MODERATOR"})
    public void getDeletePostTest() throws Exception {
        this.mockMvc.perform(get("/deletePost?tid=1&pid=1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/topic?id=1"));
    }

    @Test
    @WithMockUser(roles = {"USER", "MODERATOR"})
    public void getCreateTopicPageTest() throws Exception {
        this.mockMvc.perform(get("/createTopic"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("createTopic"));
    }

    @Test
    @WithMockUser(roles = {"MODERATOR"})
    public void getUpdateTopicPageTest() throws Exception {
        this.mockMvc.perform(get("/updateTopic?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("updateTopic"));
    }

    @Test
    @WithMockUser(roles = {"MODERATOR"})
    public void getDeleteTopicTest() throws Exception {
        this.mockMvc.perform(get("/deleteTopic?id=1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

}