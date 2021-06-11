package com.hjkportfolio.hjk.user;

import com.hjkportfolio.hjk.MyBatisConfig;
import com.hjkportfolio.hjk.exception.InsertFailException;
import com.hjkportfolio.hjk.mapper.AdminMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginService {

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
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyBatisConfig.class);
        LoginController loginController = applicationContext.getBean("loginController", LoginController.class);

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
