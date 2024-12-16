package ru.itmentor.spring.boot_security.demo.dao;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Role getRoleById(int id);

    @EntityGraph(attributePaths = "roles")
    Optional<User> findByName(String name);
}
