package ru.itmentor.spring.boot_security.demo.service;

import ru.itmentor.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RolesService {
    Role addNewRole(Role role);
    void deleteRole(Role role);
    List<Role> getAllRoles();
    Role getRoleById(int id);
}
