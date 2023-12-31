package com.spring.security.spring.boot.security.service;

import com.spring.security.spring.boot.security.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> findAllRole();
    void addDefaultRole();
    Set<Role> findByIdRoles(List<Long>roles);
}
