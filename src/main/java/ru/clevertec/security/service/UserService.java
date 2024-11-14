package ru.clevertec.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.clevertec.security.dto.UserDto;
import ru.clevertec.security.util.InitData;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private static List<UserDto> list = InitData.init();

    public List<UserDto> findAll() {
        return list;
    }

    public UserDto findById(Long id) {
        return list.stream().filter(u -> u.getUserId().equals(id)).toList().getFirst();
    }

    public UserDto create(UserDto userDto) {
        list.add(userDto);
        return userDto;
    }

    public void update(Long id, UserDto userDto) {
        list.stream().filter(u -> u.getUserId().equals(id)).map(u -> {
            u.setAge(userDto.getAge());
            u.setUsername(userDto.getUsername());
            u.setPassword(userDto.getPassword());
            u.setLastName(userDto.getLastName());
            u.setFirstName(userDto.getFirstName());
            return u;
        });
    }

    public void delete(Long id) {
        UserDto first = list.stream().filter(u -> u.getUserId().equals(id)).toList().getFirst();
        list.remove(first.getUserId());
    }
}
