package com.example.graphqlspringstart.domain;

import java.util.Arrays;
import java.util.List;

import static com.example.graphqlspringstart.domain.Blog.BLOGS;
import static com.example.graphqlspringstart.domain.User.USERS;

public record Comment(String id, String content, Blog blog, User commentator) {

    public static List<Comment> COMMENTS = Arrays.asList(
            new Comment("1", "comment number 1", BLOGS.getFirst(), USERS.getFirst()),
            new Comment("2", "comment number 2", BLOGS.getFirst(), USERS.get(2)),
            new Comment("3", "comment number 3", BLOGS.get(1), USERS.get(2)),
            new Comment("4", "comment number 4", BLOGS.get(2), USERS.getFirst())
    );
}
