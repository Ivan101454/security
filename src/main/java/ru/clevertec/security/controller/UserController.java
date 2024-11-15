package ru.clevertec.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.clevertec.security.dto.UserDto;
import ru.clevertec.security.enums.Role;
import ru.clevertec.security.service.UserService;
import ru.clevertec.security.util.InitData;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/users";
    }

    @GetMapping("user/{id}")
    public String findBYId(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", Role.values());
        return "user/user";
    }
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("roles", Role.values());
        return "user/registration";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute UserDto userDto) {
        Long userId = userService.findAll().getLast().getUserId();
        userDto.setUserId(++userId);
        userService.create(userDto);
        return "redirect:/users";
    }
    @GetMapping("/enter")
    public String enter() {
        return "user/enter";
    }
    @PostMapping("/enter")
    public String enter(@RequestParam String username, @RequestParam String password) {
        if(!userService.findAll().stream().filter(u -> u.getUsername().equals(username) &&
                u.getPassword().equals(password)).toList().isEmpty()) {
            return "redirect:/users";
        } else {
            return "redirect:/enter";
        }
    }
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute UserDto userDto) {
        userService.update(id, userDto);
        return "redirect:/users/{id}";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
