package ru.job4j.forum.repository;

import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.Topic;
import ru.job4j.forum.model.User;

import java.util.Collection;

public interface Store {

    void saveTopic(Topic topic);

    Collection<Topic> getAllTopics();

    Topic getTopicById(int id);

    void deleteTopicById(int id);

    User getUserByUserName(String userName);

    void savePost(int topicId, Post post);

    Post getPostByTopicIdAndPostId(int topicId, int postId);

    void deletePostByTopicIdAndPostId(int topicId, int postId);

}