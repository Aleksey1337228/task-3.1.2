package ru.itmentor.spring.boot_security.demo.service;





import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {
    User addUser(User user);
    void delete(User user);
    Optional<User> findById(int id);
    List<User> getAll();
    void deleteUserById(int id);
    User findUserById(int id);
    Set<Role> getRole(Set<String> rolesId);
}
