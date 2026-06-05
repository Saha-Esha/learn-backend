package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
    
    @GetMapping("/hello1")
    public String sayHello() {
        return "find the port working";
    }
    
}
