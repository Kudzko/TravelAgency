package by.andersen.kudko.controller;

import by.andersen.kudko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @Autowired
    UserService userService;

    @GetMapping("/say-hello")
    public String sayHello() {
        return "hello_world";
    }

    @GetMapping("/users")
    public void getUsers() {
        System.out.println(userService.getAllUsers());
    }
}
