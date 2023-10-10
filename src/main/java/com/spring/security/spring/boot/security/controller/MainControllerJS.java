package com.spring.security.spring.boot.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainControllerJS {
    @GetMapping("/index")
    public String mainPage() {
        return "/javaScript/indexMain";
    }
}
