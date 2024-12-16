package ru.itmentor.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.RolesService;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Контроллер для админа
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RolesService rolesService;

    @Autowired
    public AdminController(UserService userService, RolesService rolesService, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.rolesService = rolesService;
    }

    // Получение списка всех пользователей
    @GetMapping
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        model.addAttribute("roles", rolesService.getAllRoles());
        model.addAttribute("newUser", new User()); // Для формы добавления нового пользователя
        return "admin"; // Имя HTML-шаблона
    }

    // Добавление нового пользователя
    @PostMapping("/add")
    public String addUser(@ModelAttribute("newUser") User user, @RequestParam Set<String> roles) {
        user.setRoles(userService.getRole(roles)); // Устанавливаем роли
        userService.addUser(user);
        return "redirect:/admin";
    }

    // Удаление пользователя
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    // Обновление информации о пользователе
    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam Set<String> roles) {
        Set<Role> roleSet = userService.getRole(roles); // Устанавливаем роли
        user.setRoles(roleSet);        // Присваиваем роли пользователю
        User existingUser = userService.findUserById(user.getId());
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            user.setPassword(existingUser.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword())); // Шифруем новый пароль
        }
        userService.addUser(user); // Сохраняем изменения
        return "redirect:/admin";
    }
}
