package com.example.ec.web;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.ec.domain.User;
import com.example.ec.service.UserService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    @MockBean
    private UserService service;
    @Test
    public void login() {
    	
    }

    @Test
    public void getAllUsers() {
//    	List<User> allUsers = service.getAll();
//    	List<String> allNames = allUsers.stream().map(u -> u.getUsername()).collect(Collectors.toList());
//    	System.out.println("All Names: " + allNames);
//    	assertTrue(allNames.contains("wally"));
//    	assertTrue(allNames.contains("csr_jane"));
//    	assertTrue(allNames.contains("csr_mark"));
//    	assertTrue(allNames.contains("admin"));
    }
}