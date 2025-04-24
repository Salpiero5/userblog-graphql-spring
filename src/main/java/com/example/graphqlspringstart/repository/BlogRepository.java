package com.example.graphqlspringstart.repository;

import com.example.graphqlspringstart.domain.Blog;
import com.example.graphqlspringstart.domain.BlogFilter;
import com.example.graphqlspringstart.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.graphqlspringstart.domain.Blog.BLOGS;
import static com.example.graphqlspringstart.domain.User.USERS;

@Repository
public class BlogRepository {

    public List<Blog> getBlogs() {
        return BLOGS;
    }

    public Blog getById(String id) {
        return BLOGS.stream()
                .filter(blog -> blog.id().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Blog> getByUserId(String id) {
        return BLOGS.stream()
                .filter(blog -> blog.userId().equals(id))
                .toList();
    }

    public List<Blog> getByFilter(BlogFilter filter) {

        if (filter == null) {
            return null;
        }

        if (filter.published() != null) {
            return BLOGS.stream()
                    .filter(blog -> blog.published().equals(filter.published()))
                    .toList();
        }
        return null;
    }
}