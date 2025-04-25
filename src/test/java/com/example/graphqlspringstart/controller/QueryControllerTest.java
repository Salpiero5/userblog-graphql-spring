package com.example.graphqlspringstart.controller;


import com.example.graphqlspringstart.repository.BlogRepository;
import com.example.graphqlspringstart.repository.CommentRepository;
import com.example.graphqlspringstart.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

import static com.example.graphqlspringstart.domain.User.USERS;
import static org.mockito.Mockito.when;

@GraphQlTest(QueryController.class)
@AutoConfigureHttpGraphQlTester
public class QueryControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private BlogRepository blogRepository;

    @MockBean
    private CommentRepository commentRepository;

    @Test
    void shouldGetFirstUser() {

        when(userRepository.getById("1")).thenReturn(USERS.getFirst());

        this.graphQlTester
//                .documentName("userById")
                .document("""
                        query {
                            userById(id: "1") {
                                id
                                firstName
                                email
                                yearOfBirth
                            }
                        }
                        """)
                .execute()
                .path("userById")
                .matchesJson("""
                           {"id":"1","firstName":"salman","email":"sa@email.com","yearOfBirth":2000}
                        """);
    }

}
