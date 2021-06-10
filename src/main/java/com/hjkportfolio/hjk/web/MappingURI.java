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

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest req){

        HttpSession session = req.getSession();
        session.invalidate();

        return "redirect:/";
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
    public String updateList(Model model){

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyBatisConfig.class);
        UpdateController updateController = applicationContext.getBean("updateController", UpdateController.class);
        List<UpdateBean> updateBeanList = updateController.getUpdateList();

        model.addAttribute("updateBeanList", updateBeanList);

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

    @GetMapping("update")
    public String Update(){
        return "update";
    }

    /**
     *
     * 사용자 로그인 수행 메소드
     *
     * @param adminBean 사용자가 입력한 로그인 정보(id, password)
     * @param session HttpSession
     * @param rttr
     * @return
     */
    @PostMapping("login/access")
    public String loginAccess(AdminBean adminBean, HttpSession session, RedirectAttributes rttr){
        LoginController loginController = new LoginController();

        // 1. 데이터를 받아온다.
        Optional<AdminBean> optional = loginController.getAdminBeanInDatabase(adminBean);

        AdminBean adminBeanInDatabase;

        // 2. 받아온 데이터가 데이터베이스에 있는 값과 일치하는지 확인한다.
        if(optional.isPresent()){
            adminBeanInDatabase = optional.get();

            // 3. 일치한다면 로그인 성공 후 메인 화면으로 돌아간다.
            session.setAttribute("uid", adminBeanInDatabase.getUid());
            session.setAttribute("id", adminBeanInDatabase.getAdmin_id());
            session.setAttribute("name", adminBeanInDatabase.getName());

            return "redirect:/";

        }else{
            // 4. 불일치한다면 팝업을 띄운 뒤 로그인 화면으로 돌아간다.
            rttr.addFlashAttribute("message", "아이디나 비밀번호의 값이 올바르지 않습니다.");

            return "redirect:/login";
        }

    }

}
