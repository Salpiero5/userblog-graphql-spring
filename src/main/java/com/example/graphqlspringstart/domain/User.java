package com.example.graphqlspringstart.domain;

import jakarta.annotation.Nullable;

import java.util.Arrays;
import java.util.List;

public record User(String id, String firstName, @Nullable String email, @Nullable Integer yearOfBirth) {

    public static final List<User> USERS = Arrays.asList(
            new User("1", "sal", "sa@email.com", 2000),
            new User("2", "mar", "mar@email.com", null),
            new User("3", "wick", null, 1980)
    );
}
