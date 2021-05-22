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

    @GetMapping("header")
    public String header(){
        return "header";
    }

    @GetMapping("footer")
    public String footer(){
        return "footer";
    }

    @GetMapping("nav")
    public String nav(){
        return "nav";
    }

    @GetMapping("contact")
    public String contact(){
        return "contact";
    }

    @GetMapping("info")
    public String info(){
        return "info";
    }

    @GetMapping("project")
    public String project(){
        return "project";
    }

    @GetMapping("project-list")
    public String projectList(){
        return "project-list";
    }

    @GetMapping("tech-stack")
    public String techStack(){
        return "tech-stack";
    }

    @GetMapping("update-list")
    public String updateList(){
        return "update-list";
    }

    @GetMapping("write-project")
    public String writeProject(){
        return "write-project";
    }

    @GetMapping("write-update")
    public String writeUpdate(){
        return "write-update";
    }
}
