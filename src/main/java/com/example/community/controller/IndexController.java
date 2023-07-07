package com.example.community.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
created by angelee 20230703
 */

@Controller
public class IndexController {

    @GetMapping("/")
    public String Index(){
        return "index";

    }
}
