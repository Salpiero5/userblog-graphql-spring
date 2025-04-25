package com.example.graphqlspringstart.repository;

import com.example.graphqlspringstart.domain.Blog;
import com.example.graphqlspringstart.domain.BlogFilter;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.graphqlspringstart.domain.Blog.BLOGS;

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
                    .filter(blog -> blog.isPublished().equals(filter.published()))
                    .toList();
        }

        if (filter.content() != null) {
            return BLOGS.stream()
                    .filter(blog -> blog.content().equals(filter.content()))
                    .toList();
        }
        return null;
    }
}