package com.hjkportfolio.hjk.project;

import com.hjkportfolio.hjk.post.Criteria;
import com.hjkportfolio.hjk.post.ProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("project")
    public String project(int id, Model model){

        ProjectVO projectVO = projectService.getProject(id);

        model.addAttribute("project", projectVO);

        return "project";
    }

    @GetMapping("project-list")
    public String projectList(Model model, Criteria criteria){
        List<ProjectVO> projectVOList = projectService.getProjectList(criteria);

        model.addAttribute("projectList", projectVOList);

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

    @GetMapping("project/delete")
    public String deleteProject(int id, HttpSession httpSession){

        projectService.deleteProject(id);

        return "redirect:/project-list";
    }
}
