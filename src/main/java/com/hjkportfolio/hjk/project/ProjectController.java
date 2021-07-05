package com.hjkportfolio.hjk.project;

import com.hjkportfolio.hjk.post.ProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("project")
    public String project(){
        return "project";
    }

    @GetMapping("project-list")
    public String projectList(){



        return "project-list";
    }

    @GetMapping("write-project")
    public String writeProject(){
        return "write-project";
    }

    @PostMapping("project/update")
    public String updateProject(ProjectVO projectVO, HttpSession httpSession){

        System.out.println("projectVO : " + projectVO.toString());
        projectService.insertProjectTable(projectVO);

        return "redirect:/project-list";
    }

}
