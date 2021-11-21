package ru.job4j.forum.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.job4j.forum.model.Topic;

import java.util.Collection;

public interface TopicRepository extends PagingAndSortingRepository<Topic, Long> {

    @EntityGraph(attributePaths = { "author", "posts" })
    Collection<Topic> findAll();

    Topic findTopicById(int id);

    void deleteTopicById(int id);

}