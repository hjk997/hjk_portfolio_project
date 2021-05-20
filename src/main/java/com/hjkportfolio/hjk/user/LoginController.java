package com.hjkportfolio.hjk.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("")
    public String index(){

        return "index";
    }

    @GetMapping("login")
    public String login(){
        System.out.println("login");

        return "login";
    }

    @GetMapping("test")
    public String test(Model model){

        model.addAttribute("data", "신기하다");
        return "test";
    }
}
