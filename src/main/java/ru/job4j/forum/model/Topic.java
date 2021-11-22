package ru.job4j.forum.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "topics")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private Date created = new Date(System.currentTimeMillis());
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @OrderBy("id ASC")
    private List<Post> posts = new ArrayList<>();

    public static Topic of(int id, String name, String description, User author) {
        Topic newTopic = new Topic();
        newTopic.id = id;
        newTopic.name = name;
        newTopic.description = description;
        newTopic.author = author;
        return newTopic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Topic topic = (Topic) o;
        return id == topic.id && Objects.equals(name, topic.name)
                && Objects.equals(description, topic.description)
                && Objects.equals(created, topic.created)
                && Objects.equals(author, topic.author)
                && Objects.equals(posts, topic.posts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, created, author, posts);
    }

    @Override
    public String toString() {
        return "Topic { " + "id=" + id + ", name='" + name + "', description='" + description
                + "', created=" + created + ", author=" + author
                + ", posts=" + posts + " }";
    }
}