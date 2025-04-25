package com.example.graphqlspringstart.repository;

import com.example.graphqlspringstart.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.graphqlspringstart.domain.Comment.COMMENTS;

@Repository
public class CommentRepository {

    public List<Comment> getComments() {
        return COMMENTS;
    }

    public List<Comment> getByBlogId(String blogId) {
        return COMMENTS.stream()
                .filter(cm -> cm.blog().id().equals(blogId))
                .toList();
    }

    public List<Comment> getByUserId(String userId) {
        return COMMENTS.stream()
                .filter(cm -> cm.commentator().id().equals(userId))
                .toList();
    }
}
