package ru.job4j.forum.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.Topic;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.AuthorityRepository;
import ru.job4j.forum.repository.PostRepository;
import ru.job4j.forum.repository.TopicRepository;
import ru.job4j.forum.repository.UserRepository;

import java.util.Collection;
import java.util.List;

@Service
public class SpringDataService implements ForumService {

    private TopicRepository topicRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;

    public SpringDataService(TopicRepository topicRepository,
                             PostRepository postRepository,
                             UserRepository userRepository,
                             AuthorityRepository authorityRepository) {
        this.topicRepository = topicRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public void saveTopic(Topic topic) {
        topicRepository.save(topic);
    }

    @Override
    public Collection<Topic> getAllTopics() {
        return (Collection<Topic>) topicRepository.findAll(Sort.by("created").ascending());
    }

    @Override
    public Topic getTopicById(int id) {
        return topicRepository.findTopicById(id);
    }

    @Override
    @Transactional
    public void deleteTopicById(int id) {
        topicRepository.deleteTopicById(id);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public Authority findAuthorityByName(String nameOfAuthority) {
        return authorityRepository.findByAuthority(nameOfAuthority);
    }

    @Override
    public void savePost(int topicId, Post post) {
        Topic foundTopic = topicRepository.findTopicById(topicId);
        if (post.getId() == 0) {
            foundTopic.addPost(post);
        } else {
            List<Post> posts = foundTopic.getPosts();
            for (int i = 0; i < posts.size(); i++) {
                if (posts.get(i).getId() == post.getId()) {
                    posts.set(i, post);
                    break;
                }
            }
        }
        topicRepository.save(foundTopic);
    }

    @Override
    public Post getPostByTopicIdAndPostId(int topicId, int postId) {
        return postRepository.findPostById(postId);
    }

    @Override
    public void deletePostByTopicIdAndPostId(int topicId, int postId) {
        Topic foundTopic = topicRepository.findTopicById(topicId);
        List<Post> posts = foundTopic.getPosts();
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getId() == postId) {
                posts.remove(i);
                break;
            }
        }
        topicRepository.save(foundTopic);
    }
}
