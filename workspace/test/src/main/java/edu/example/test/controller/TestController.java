package edu.example.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/test")
    public void test(){
        System.out.println("--- test() ");
    }

    @GetMapping("/login")
    public void loginForm(){
        System.out.println("--- login() ");
    }

    @GetMapping("/list")
    public void list(){
        System.out.println("--- list() ");
    }
}
