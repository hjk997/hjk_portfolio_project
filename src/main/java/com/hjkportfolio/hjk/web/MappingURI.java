package com.hjkportfolio.hjk.web;

import com.hjkportfolio.hjk.MyBatisConfig;
import com.hjkportfolio.hjk.update.UpdateBean;
import com.hjkportfolio.hjk.update.UpdateController;
import com.hjkportfolio.hjk.user.AdminBean;
import com.hjkportfolio.hjk.user.LoginController;
import org.apache.ibatis.annotations.Update;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

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


    @GetMapping("write-project")
    public String writeProject(){
        return "write-project";
    }


}
