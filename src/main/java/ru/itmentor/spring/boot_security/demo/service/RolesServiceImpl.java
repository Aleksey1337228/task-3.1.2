package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.dao.RoleRepository;
import ru.itmentor.spring.boot_security.demo.model.Role;

import java.util.List;

@Service
public class RolesServiceImpl implements RolesService {
    private final RoleRepository roleRepository;
    @Autowired
    public RolesServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public Role addNewRole(Role role) {
        return roleRepository.saveAndFlush(role);
    }
    @Override
    public void deleteRole(Role role) {
        roleRepository.deleteById(role.getId());
    }
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    @Override
    public Role getRoleById(int id) {
        // Проверяем, существует ли роль с таким ID
        return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
    }
}
