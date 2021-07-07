package com.hjkportfolio.hjk.project;

import com.hjkportfolio.hjk.post.Criteria;
import com.hjkportfolio.hjk.post.PageMaker;
import com.hjkportfolio.hjk.post.ProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

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
        int total = projectService.getTotal();

        // 페이지 값으로 들고온 값이 total보다 크면 페이지를 표시할 수 없다는 문구를 출력함
        if(!criteria.isPageNumValid(total)) {
            // 에러가 발생했을 때는 이미지를, 아닐 때는 표를 출력
            criteria.setPageNum((int) total / 10);
            model.addAttribute("checkCode", 0);
            model.addAttribute("pageMaker", new PageMaker(criteria, projectService.getTotal()));
        }else{
            List<ProjectVO> projectVOList = projectService.getProjectList(criteria);

            model.addAttribute("projectList", projectVOList);
            model.addAttribute("checkCode", 1);
            model.addAttribute("pageMaker", new PageMaker(criteria, projectService.getTotal()));
        }

        return "project-list";
    }

    @GetMapping("write-project")
    public String writeProject(){

        return "write-project";
    }

    @PostMapping("write-project")
    public String updateProject(int uid, Model model){

        model.addAttribute("project", projectService.getProject(uid));

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
