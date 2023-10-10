package com.spring.security.spring.boot.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {


    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }


}
