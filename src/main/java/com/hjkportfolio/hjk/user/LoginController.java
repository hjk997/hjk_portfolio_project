package com.hjkportfolio.hjk.user;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class LoginController {

    @GetMapping("")
    public String index(){

        return "index";
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("logout")
    public String logout(HttpSession session){

        session.invalidate();

        return "redirect:/";
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

    @PostMapping("login/access")
    public String loginAccess(AdminBean adminBean, HttpSession session, RedirectAttributes rttr){

        String id = adminBean.getAdmin_id();
        String pw = adminBean.getPassword();

        // 1. 데이터를 받아온다.
        System.out.println(id + " " + pw);

        AdminBean adminBeanInDatabase = getAdminBeanInDatabase(id);

        // 2. 받아온 데이터가 데이터베이스에 있는 값과 일치하는지 확인한다.
        if(adminBeanInDatabase != null && isLogin(adminBeanInDatabase, adminBean)){
            // 3. 일치한다면 로그인 성공 후 메인 화면으로 돌아간다.
            System.out.println("success");

            session.setAttribute("uid", adminBeanInDatabase.getUid());
            session.setAttribute("id", adminBeanInDatabase.getAdmin_id());
            session.setAttribute("name", adminBeanInDatabase.getName());

            return "redirect:/";
        }else{
            // 4. 불일치한다면 팝업을 띄운 뒤 로그인 화면으로 돌아간다.
            System.out.println("fail");

            rttr.addFlashAttribute("message", "아이디나 비밀번호의 값이 올바르지 않습니다.");

            return "redirect:/login";
        }

    }

    private AdminBean getAdminBeanInDatabase(String id){
        String resource = "mybatis-config.xml";
        InputStream inputStream;

        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            try (SqlSession session = sqlSessionFactory.openSession()) {
                AdminBean user = session.selectOne("mapper.AdminLogin.selectUser", id);
                return user;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private boolean isLogin(AdminBean adminBean1, AdminBean adminBean2) {
        if(adminBean1.getPassword().equals(adminBean2.getPassword())){
            return true;
        }

        return false;
    }

}
