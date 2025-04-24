package com.example.graphqlspringstart.repository;

import com.example.graphqlspringstart.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.graphqlspringstart.domain.User.USERS;

@Repository
public class UserRepository {

    public List<User> getUsers() {
        return USERS;
    }

    public User getById(String id) {
        return USERS.stream()
                .filter(user -> user.id().equals(id))
                .findFirst()
                .orElse(null);
    }
}
