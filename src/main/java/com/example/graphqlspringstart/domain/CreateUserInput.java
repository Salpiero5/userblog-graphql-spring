package com.example.graphqlspringstart.domain;

import jakarta.annotation.Nullable;

public record CreateUserInput(String firstName, @Nullable String email, @Nullable Integer yearOfBirth) {
}
