package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.Topic;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.Store;

import java.util.Collection;

/*@Service*/
public class MemAndJDBCService implements ForumService {

    private Store store;

    public MemAndJDBCService(Store store) {
        this.store = store;
    }

    @Override
    public void saveTopic(Topic topic) {
        store.saveTopic(topic);
    }

    @Override
    public Collection<Topic> getAllTopics() {
        return store.getAllTopics();
    }

    @Override
    public Topic getTopicById(int id) {
        return store.getTopicById(id);
    }

    @Override
    public void deleteTopicById(int id) {
        store.deleteTopicById(id);
    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public User getUserByUserName(String userName) {
        return store.getUserByUserName(userName);
    }

    @Override
    public Authority findAuthorityByName(String nameOfAuthority) {
        return null;
    }

    @Override
    public void savePost(int topicId, Post post) {
        store.savePost(topicId, post);
    }

    @Override
    public Post getPostByTopicIdAndPostId(int topicId, int postId) {
        return store.getPostByTopicIdAndPostId(topicId, postId);
    }

    @Override
    public void deletePostByTopicIdAndPostId(int topicId, int postId) {
        store.deletePostByTopicIdAndPostId(topicId, postId);
    }

}