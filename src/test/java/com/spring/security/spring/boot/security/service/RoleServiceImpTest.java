package com.spring.security.spring.boot.security.service;


import com.spring.security.spring.boot.security.repository.RoleDAO;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RoleServiceImpTest {
    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private RoleDAO roleDAO;



}
