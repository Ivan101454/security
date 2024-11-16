package ru.clevertec.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.clevertec.security.dto.UserDto;
import ru.clevertec.security.util.InitData;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private static final List<UserDto> list = InitData.init();

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
        list.stream().filter(u -> u.getUserId().equals(id)).peek(u -> {u.setAge(userDto.getAge());
            u.setUsername(userDto.getUsername());
            u.setPassword(userDto.getPassword());
            u.setLastName(userDto.getLastName());
            u.setFirstName(userDto.getFirstName());}).toList().getFirst();
    }

    public void delete(Long id) {
        UserDto first = list.stream().filter(u -> u.getUserId().equals(id)).toList().getFirst();
        list.remove(first.getUserId());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return list.stream().filter(u -> u.getUsername().equals(username)).map(u ->
//                new org.springframework.security.core.userdetails.User(u.getUsername(),
//                        u.getPassword(),
//                        Collections.singleton(u.getRole()))).toList().getFirst();
        User.withUsername(u.getUsername())
                .password("{noop}" + u.getPassword())
                .roles(String.valueOf(Collections.singleton(u.getRole())))).toList().getFirst()
                .build();
    }
}
