package ru.clevertec.security.util;

import ru.clevertec.security.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class InitData {

    public static List<UserDto> init() {
        return new ArrayList<>(List.of(UserDto.builder().userId(1L).username("Lama123")
                        .firstName("Lamochkin").lastName("Lammm").age(35).password("12345").build(),
                UserDto.builder().userId(2L).username("Niedzwiedz")
                        .firstName("Ivan").lastName("Ivan").age(37).password("QWE").build()));
    }

    public static UserDto initEmptyUser() {
        return UserDto.builder().build();
    }
}
