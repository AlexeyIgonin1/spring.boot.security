package com.spring.security.spring.boot.security.service;

import com.spring.security.spring.boot.security.model.Role;
import com.spring.security.spring.boot.security.repository.RoleDAO;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }


    @Override
    public List<Role> findAllRole() {
        return roleDAO.findAll();
    }

    @Override
   //@PostConstruct
    public void addDefaultRole() {
        roleDAO.save(new Role("ROLE_USER"));
        roleDAO.save(new Role("ROLE_ADMIN"));

    }

    @Override
    public Set<Role> findByIdRoles(List<Long> roles) {
        return new HashSet<>(roleDAO.findAllById(roles));
    }
}
