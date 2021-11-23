package ru.job4j.forum.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.Topic;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.ForumService;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CreateAndUpdateModelTest {

    @MockBean
    private ForumService service;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldCreateNewUser() throws Exception {

        Authority userAuthority = Authority.of(1, "ROLE_USER");

        Mockito.when(service.findAuthorityByName(Mockito.any())).thenReturn(userAuthority);

        mockMvc.perform(post("/reg")
                        .param("username", "user1")
                        .param("password", "123456"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));

        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        verify(service).saveUser(argument.capture());

        Assertions.assertEquals(argument.getValue().getUsername(), "user1");
        Assertions.assertTrue(encoder.matches("123456", argument.getValue().getPassword()));
        Assertions.assertEquals(argument.getValue().getAuthority(), userAuthority);
        Assertions.assertTrue(argument.getValue().getEnabled());
    }

    @Test
    @WithMockUser
    public void shouldCreateNewTopic() throws Exception {

        mockMvc.perform(post("/saveTopic")
                        .param("id", "0")
                        .param("name", "new topic")
                        .param("description", "new description"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        ArgumentCaptor<Topic> argument = ArgumentCaptor.forClass(Topic.class);
        verify(service).saveTopic(argument.capture());

        Assertions.assertEquals(argument.getValue().getId(), 0);
        Assertions.assertEquals(argument.getValue().getName(), "new topic");
        Assertions.assertEquals(argument.getValue().getDescription(), "new description");
    }

    @Test
    @WithMockUser
    public void shouldUpdateExistTopic() throws Exception {

        Authority userAuthority = Authority.of(1, "ROLE_USER");
        User user1 = User.of(1, "user1", "123456", userAuthority);
        Topic oldTopic = Topic.of(1, "old topic", "old description", user1);

        Mockito.when(service.getTopicById(1)).thenReturn(oldTopic);

        mockMvc.perform(post("/saveTopic")
                        .param("id", "1")
                        .param("name", "new topic")
                        .param("description", "new description"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        ArgumentCaptor<Topic> argument = ArgumentCaptor.forClass(Topic.class);
        verify(service).saveTopic(argument.capture());

        Assertions.assertEquals(argument.getValue().getId(), 1);
        Assertions.assertEquals(argument.getValue().getName(), "new topic");
        Assertions.assertEquals(argument.getValue().getDescription(), "new description");
        Assertions.assertEquals(argument.getValue().getAuthor(), user1);

    }

    @Test
    @WithMockUser
    public void shouldCreateNewPost() throws Exception {

        mockMvc.perform(post("/savePost")
                        .param("id", "0")
                        .param("topicId", "1")
                        .param("text", "new comment"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/topic?id=1"));

        ArgumentCaptor<Integer> argument1 = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Post> argument2 = ArgumentCaptor.forClass(Post.class);
        verify(service).savePost(argument1.capture(), argument2.capture());

        Assertions.assertEquals(argument2.getValue().getId(), 0);
        Assertions.assertEquals(argument1.getValue(), 1);
        Assertions.assertEquals(argument2.getValue().getText(), "new comment");

    }

    @Test
    @WithMockUser
    public void shouldUpdateExistPost() throws Exception {

        Authority userAuthority = Authority.of(1, "ROLE_USER");
        User user1 = User.of(1, "user1", "123456", userAuthority);
        Post oldPost = Post.of(1, "old comment", user1);

        Mockito.when(service.getPostByTopicIdAndPostId(1, 1)).thenReturn(oldPost);

        mockMvc.perform(post("/savePost")
                        .param("id", "1")
                        .param("topicId", "1")
                        .param("text", "new comment"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/topic?id=1"));

        ArgumentCaptor<Integer> argument1 = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Post> argument2 = ArgumentCaptor.forClass(Post.class);
        verify(service).savePost(argument1.capture(), argument2.capture());

        Assertions.assertEquals(argument1.getValue(), 1);
        Assertions.assertEquals(argument2.getValue().getId(), 1);
        Assertions.assertEquals(argument2.getValue().getText(), "new comment");

    }

}