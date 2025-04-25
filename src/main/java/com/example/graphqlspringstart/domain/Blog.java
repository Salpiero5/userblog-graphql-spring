package com.example.graphqlspringstart.domain;

import java.util.Arrays;
import java.util.List;

public record Blog(String id, String title, String content, Boolean isPublished, String userId) implements Article {

    public static List<Blog> BLOGS = Arrays.asList(
            new Blog("1", "Joshua", "Bloch", true, "1"),
            new Blog("2", "Douglas", "Adams", false, "1"),
            new Blog("3", "Bill", "Bryson", false, "2"),
            new Blog("4", "Bill", "Bryson", false, "3"),
            new Blog("5", "Bill", "Bryson", false, "3")
    );
}