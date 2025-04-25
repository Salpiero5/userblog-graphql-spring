package com.example.graphqlspringstart.controller;

import com.example.graphqlspringstart.domain.CreateUserInput;
import com.example.graphqlspringstart.domain.User;
import com.example.graphqlspringstart.repository.UserRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MutationController {

    private final UserRepository userRepository;

    public MutationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * mutation($input:CreateUserInput!) {
     *
     *   createUser(input: $input) {
     *     id
     *     firstName
     *     yearOfBirth
     *     email
     *   }
     * }
     *
     * {
     *   "input": {
     *     "firstName":  "JOE"
     *
     *   }
     * }
     */
    @MutationMapping
    public User createUser(@Argument CreateUserInput input) {
        return userRepository.createUser(input);
    }
}
