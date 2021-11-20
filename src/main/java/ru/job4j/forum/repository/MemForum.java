package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.Topic;
import ru.job4j.forum.model.User;

import java.util.*;

@Repository
public class MemForum implements Store {

    private Map<Integer, Topic> topics = new HashMap<>();
    private Map<Integer, User> users = new HashMap<>();
    private Map<Integer, Authority> authorities = new HashMap<>();
    private int topicCounter = 1;
    private int postCounter = 1;

    public MemForum() {
        init();
    }

    @Override
    public void saveTopic(Topic topic) {
        if (topic.getId() == 0) {
            topic.setId(topicCounter++);
        }
        topics.put(topic.getId(), topic);
    }

    @Override
    public Collection<Topic> getAllTopics() {
        return topics.values();
    }

    @Override
    public Topic getTopicById(int id) {
        return topics.get(id);
    }

    @Override
    public void deleteTopicById(int id) {
        topics.remove(id);
    }

    @Override
    public User getUserByUserName(String userName) {
        User rsl = new User();
        for (User user : users.values()) {
            if (userName.equals(user.getUsername())) {
                rsl = user;
                break;
            }
        }
        return rsl;
    }

    @Override
    public void savePost(int topicId, Post post) {
        if (post.getId() == 0) {
            post.setId(postCounter++);
            topics.get(topicId).addPost(post);
        } else {
            List<Post> posts = topics.get(topicId).getPosts();
            for (Post p : posts) {
                if (p.getId() == post.getId()) {
                    posts.set(posts.indexOf(p), post);
                    break;
                }
            }
        }
    }

    @Override
    public Post getPostByTopicIdAndPostId(int topicId, int postId) {
        Post rsl = new Post();
        for (Post post : topics.get(topicId).getPosts()) {
            if (post.getId() == postId) {
                rsl = post;
                break;
            }
        }
        return rsl;
    }

    @Override
    public void deletePostByTopicIdAndPostId(int topicId, int postId) {
        List<Post> posts = topics.get(topicId).getPosts();
        for (Post post : posts) {
            if (post.getId() == postId) {
                posts.remove(post);
                break;
            }
        }
    }

    private void init() {

        Authority moderator = Authority.of(1, "ROLE_MODERATOR");
        Authority user = Authority.of(2, "ROLE_USER");

        authorities.put(moderator.getId(), moderator);
        authorities.put(user.getId(), user);

        User admin = User.of(1, "admin", "123456", moderator);
        User user1 = User.of(2, "user1", "123456", user);
        User user2 = User.of(3, "user2", "123456", user);
        User user3 = User.of(4, "user3", "123456", user);

        users.put(admin.getId(), admin);
        users.put(user1.getId(), user1);
        users.put(user2.getId(), user2);
        users.put(user3.getId(), user3);

        Topic topic1 = Topic.of(0, "О чем и для чего этот форум?", "Стартовый пост 1", admin);
        Post post1 = Post.of(0, "Post 1. Bla-bla-bla Bla-bla-bla Bla-bla-bla Bla-bla-bla", user1);
        Post post2 = Post.of(0, "Post 2. Bla-bla-bla Bla-bla-bla Bla-bla-bla Bla-bla-bla", user2);
        Post post3 = Post.of(0, "Post 3. Bla-bla-bla Bla-bla-bla Bla-bla-bla Bla-bla-bla", user3);
        saveTopic(topic1);
        savePost(1, post1);
        savePost(1, post2);
        savePost(1, post3);

        Topic topic2 = Topic.of(0, "Правила для участников форума", "Стартовый пост 2", admin);
        Post post4 = Post.of(0, "Post 4. Bla-bla-bla Bla-bla-bla Bla-bla-bla Bla-bla-bla", admin);
        Post post5 = Post.of(0, "Post 5. Bla-bla-bla Bla-bla-bla Bla-bla-bla Bla-bla-bla", user2);
        Post post6 = Post.of(0, "Post 6. Bla-bla-bla Bla-bla-bla Bla-bla-bla Bla-bla-bla", user3);
        saveTopic(topic2);
        savePost(2, post4);
        savePost(2, post5);
        savePost(2, post6);

        Topic topic3 = Topic.of(0, "Все что связано с Java Core", "Стартовый пост 3", user1);
        Post post7 = Post.of(0, "Post 7. Bla-bla-bla Bla-bla-bla Bla-bla-bla Bla-bla-bla", user1);
        Post post8 = Post.of(0, "Post 8. Bla-bla-bla Bla-bla-bla Bla-bla-bla Bla-bla-bla", admin);
        Post post9 = Post.of(0, "Post 9. Bla-bla-bla Bla-bla-bla Bla-bla-bla Bla-bla-bla", user3);
        saveTopic(topic3);
        savePost(3, post7);
        savePost(3, post8);
        savePost(3, post9);

        Topic topic4 = Topic.of(0, "Все что связано с Java Collection", "Стартовый пост 4", user2);
        Topic topic5 = Topic.of(0, "Все что связано с Java Spring", "Стартовый пост 5", user3);

        saveTopic(topic4);
        saveTopic(topic5);

    }

}