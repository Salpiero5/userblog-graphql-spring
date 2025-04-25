package com.example.graphqlspringstart.repository;

import com.example.graphqlspringstart.domain.CreateUserInput;
import com.example.graphqlspringstart.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;

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

    public List<User> getByName(String namePart) {
        return USERS.stream()
                .filter(user -> user.firstName().contains(namePart))
                .toList();
    }

    // There is no DB behind it so pushing data is temporary
    public User createUser(CreateUserInput input) {

        Random random = new Random();
        int randomInt = random.nextInt(1000);
        String randomId = String.valueOf(randomInt);


        User addedUser = new User(randomId, input.firstName(), null, null);
        User.addUser(addedUser);

        return addedUser;
    }
}
