package com.example.ec.service;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.*;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.ec.domain.User;


@SpringBootTest
@Transactional
public class UserServiceIntegrationTest {
    @Autowired
    private UserService service;
    @Test
    public void signup() {
        Optional<User> user = service.signup("dummyUsername", "dummypassword", "john", "doe");
        assertThat(user.get().getPassword(), not("dummypassword"));
        System.out.println("Encoded Password = " + user.get().getPassword());
//        Encoded Password = $2a$12$LqBbn9sjhN/N7WoSp6ukYO4Q8ZMO/yhRXXDk5eZDxy6sK6CooG1RC
    }
    
    
}