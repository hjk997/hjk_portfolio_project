package com.hjkportfolio.hjk.user;

import com.hjkportfolio.hjk.MyBatisConfig;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    SqlSession sqlSession;

    /**
     *
     * 사용자가 입력한 id가 데이터베이스에 있는지 조회한다.
     *
     * @param adminBean - 사용자가 입력한 id
     * @return 데이터베이스 조회 후 나온 id
     */
    public Optional<AdminBean> getAdminBeanInDatabase(AdminBean adminBean) {
        try {
            MyBatisConfig myBatisConfig = new MyBatisConfig();
            LoginService loginService = myBatisConfig.loginService();

            AdminBean optional = loginService.getAdminBean(adminBean);

            return Optional.ofNullable(optional);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

}
