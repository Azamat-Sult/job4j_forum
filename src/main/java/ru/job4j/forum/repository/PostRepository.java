package ru.job4j.forum.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.job4j.forum.model.Post;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {

    @EntityGraph(attributePaths = { "author" })
    Post findPostById(int id);

}