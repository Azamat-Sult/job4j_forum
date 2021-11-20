package ru.job4j.forum.model;

import java.util.Date;
import java.util.Objects;

public class Post {

    private int id;
    private String text;
    private User author;
    private Date created = new Date(System.currentTimeMillis());

    public static Post of(int id, String text, User author) {
        Post newPost = new Post();
        newPost.id = id;
        newPost.text = text;
        newPost.author = author;
        return newPost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id && Objects.equals(text, post.text)
                && Objects.equals(author, post.author)
                && Objects.equals(created, post.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, author, created);
    }

    @Override
    public String toString() {
        return "Post { " + "id=" + id + ", text='" + text
                + "', author=" + author + ", created=" + created + " }";
    }
}