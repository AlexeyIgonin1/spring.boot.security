package com.spring.security.spring.boot.security.repository;

import com.spring.security.spring.boot.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO extends JpaRepository<Role, Long> {
}
