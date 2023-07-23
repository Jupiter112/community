package com.example.community.controller;

import com.example.community.mapper.UserMapper;
import com.example.community.model.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
created by angelee 20230703
 */

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String Index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie: cookies){
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                if(token==null){
                    System.out.println("token is null");
                }
                User user = userMapper.findByToken(token);
                if(user!=null){
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }


        return "index";

    }
}
