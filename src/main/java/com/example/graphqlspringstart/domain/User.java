package com.example.graphqlspringstart.domain;

import jakarta.annotation.Nullable;

import java.util.ArrayList;

public record User(String id, String firstName, @Nullable String email, @Nullable Integer yearOfBirth) {

    public static ArrayList<User> USERS = new ArrayList<>();

    static {
        USERS.add(new User("1", "salman", "sa@email.com", 2000));
        USERS.add(new User("2", "mark", "mar@email.com", null));
        USERS.add(new User("3", "wick", null, 1980)
        );
    }

    public static void addUser(User user) {
        USERS.add(user);
    }
}
