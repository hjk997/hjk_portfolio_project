package com.hjkportfolio.hjk.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MappingURI {

    @GetMapping("")
    public String index(){

        return "index";
    }

    @GetMapping("test")
    public String test(Model model){
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

    @GetMapping("modal")
    public String modal(){
        return "modal";
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

    @GetMapping("tech-stack")
    public String techStack(){
        return "tech-stack";
    }

}
