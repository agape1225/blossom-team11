package com.springboot.jpa.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController implements ErrorController {

    @GetMapping({ "/", "/error" })
    public String index(HttpServletRequest request) {
        System.out.println("hello world");

        return "index";
    }

    public String getErrorPath() {
        return "/error";
    }

}