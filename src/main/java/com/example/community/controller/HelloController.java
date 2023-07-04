package com.example.community.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
created by laq 20230703
 */

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String Hello(@RequestParam(name="name") String name, Model model){
        model.addAttribute("name", name);
        return "hello";

    }
}
