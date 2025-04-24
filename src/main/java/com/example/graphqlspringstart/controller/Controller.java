package com.example.graphqlspringstart.controller;


import com.example.graphqlspringstart.domain.Blog;
import com.example.graphqlspringstart.domain.BlogFilter;
import com.example.graphqlspringstart.domain.User;
import com.example.graphqlspringstart.repository.BlogRepository;
import com.example.graphqlspringstart.repository.UserRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    private final UserRepository userRepository;
    private final BlogRepository blogRepository;

    public Controller(UserRepository userRepository, BlogRepository blogRepository) {
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
    }

    @QueryMapping
    public User userById(@Argument String id) {
        return userRepository.getById(id);
    }

    @QueryMapping
    public List<User> users() {
        return userRepository.getUsers();
    }

    @QueryMapping
    public List<Blog> blogs() {
        return blogRepository.getBlogs();
    }

    @QueryMapping
    public Blog blogById(@Argument String id) {
        return blogRepository.getById(id);
    }

    @QueryMapping
    public List<Blog> blogByUserId(@Argument String id) {
        return blogRepository.getByUserId(id);
    }

    @QueryMapping
    public List<Blog> blogByFilter(@Argument BlogFilter filter) {
        return blogRepository.getByFilter(filter);
    }

    /**
     *
     * “Hey, when someone asks for the blog field inside a
     *  User type, GraphQL uses this method to resolve it.”
     * query {
     *   users {
     *     firstName
     *     blog {
     *       title
     *       published
     *     }
     *   }
     * }
     */
    @SchemaMapping(typeName = "User", field = "blog")
    public List<Blog>getBlogsForUser(User user) {
        return blogRepository.getByUserId(user.id());
    }

    /**
     *
     * “Hey, when someone asks for the user field inside a
     *  Blog type, GraphQL uses this method to resolve it.”
     * query {
     *  blogs {
     *     id
     *     title
     *     content
     *     published
     *     user {
     *       id
     *       firstName
     *     }
     *   }
     * }
     */
    @SchemaMapping(typeName = "Blog", field = "user")
    public User getUserForBlog(Blog blog) {
        return userRepository.getById(blog.userId());
    }
}
