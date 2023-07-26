package com.example.community.controller;

import com.example.community.dto.PaginationDTO;
import com.example.community.mapper.UserMapper;
import com.example.community.model.User;
import com.example.community.service.QuestionService;
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

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String Index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "2") Integer size
    ){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null && cookies.length!=0) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        PaginationDTO paginations = questionService.list(page, size);
        model.addAttribute("paginations", paginations);

        return "index";

    }
}
