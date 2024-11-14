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

    private static final List<UserDto> list = InitData.init();

    public List<UserDto> findAll() {
        return list;
    }

    public UserDto findById(Long id) {
        return list.stream().filter(u -> u.getUserId().equals(id)).toList().getFirst();
    }

    public UserDto create(UserDto userDto) {
        return null;
    }

    public String update(Long id, UserDto userDto) {
        return null;
    }

    public void delete(Long id) {

    }
}
