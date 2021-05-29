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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

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
        // 1. 데이터를 받아온다.
        Optional<AdminBean> optional = getAdminBeanInDatabase(adminBean.getAdmin_id());

        AdminBean adminBeanInDatabase;

        if(optional.isPresent()){
            adminBeanInDatabase = optional.get();

            // 2. 받아온 데이터가 데이터베이스에 있는 값과 일치하는지 확인한다.
            if(isLogin(adminBeanInDatabase, adminBean)){
                // 3. 일치한다면 로그인 성공 후 메인 화면으로 돌아간다.
                session.setAttribute("uid", adminBeanInDatabase.getUid());
                session.setAttribute("id", adminBeanInDatabase.getAdmin_id());
                session.setAttribute("name", adminBeanInDatabase.getName());

                return "redirect:/";
            }
        }

        // 4. 불일치한다면 팝업을 띄운 뒤 로그인 화면으로 돌아간다.
        rttr.addFlashAttribute("message", "아이디나 비밀번호의 값이 올바르지 않습니다.");

        return "redirect:/login";

    }

    /**
     *
     * 사용자가 입력한 id가 데이터베이스에 있는지 조회한다.
     *
     * @param id - 사용자가 입력한 id
     * @return 데이터베이스 조회 후 나온 id
     */
    public Optional<AdminBean> getAdminBeanInDatabase(String id){
        String resource = "mybatis-config.xml";
        InputStream inputStream;

        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            try (SqlSession session = sqlSessionFactory.openSession()) {
                AdminBean user = session.selectOne("mapper.AdminLogin.selectUser", id);
                session.close();
                return Optional.ofNullable(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.ofNullable(null);
        }
    }

    public void insertAdminAccount(AdminBean testUser){
        String resource = "mybatis-config.xml";
        InputStream inputStream;

        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            try (SqlSession session = sqlSessionFactory.openSession()) {
                session.insert("mapper.AdminLogin.insertTestAdminUser",
                        testUser);
                session.commit();
                session.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 사용자가 입력한 로그인 정보와 데이터베이스에 있는 로그인 정보가 같은지 확인한다.
     * @param adminBean1 데이터베이스에서 가져온 로그인 정보
     * @param adminBean2 사용자가 입력한 로그인 정보
     * @return 로그인 가능 여부
     */
    private boolean isLogin(AdminBean adminBean1, AdminBean adminBean2) {
        if(adminBean1.getPassword().equals(adminBean2.getPassword())){
            return true;
        }

        return false;
    }

}
