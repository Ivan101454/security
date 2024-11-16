package ru.clevertec.security.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.clevertec.security.dto.UserDto;
import ru.clevertec.security.util.InitData;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    void create() {
        UserDto userDto = userService.create(InitData.initEmptyUser());
        System.out.println(userDto);
    }
}