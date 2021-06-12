package com.hjkportfolio.hjk.user;

import com.hjkportfolio.hjk.MyBatisConfig;
import com.hjkportfolio.hjk.exception.InsertFailException;
import com.hjkportfolio.hjk.mapper.AdminMapper;
import com.hjkportfolio.hjk.update.UpdateController;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class LoginController {

    SqlSession sqlSession;

    @Autowired
    AdminMapper adminMapper;

    public LoginController(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    /**
     *
     * 사용자가 입력한 id가 데이터베이스에 있는지 조회한다.
     *
     * @param adminBean - 사용자가 입력한 id
     * @return 데이터베이스 조회 후 나온 id
     */
    public Optional<AdminBean> getAdminBeanInDatabase(AdminBean adminBean) {
        try {
            AdminBean optional = getAdminBean(adminBean);

            return Optional.ofNullable(optional);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private AdminBean getAdminBean(AdminBean adminBean){
        //return new AdminBean(0, "name", "name","name");
        adminMapper = sqlSession.getMapper(AdminMapper.class);
        AdminBean adminBean1 = adminMapper.getAdminBean(adminBean);
        return adminBean1;
    }

    public void InsertAdminBean(AdminBean adminBean) throws InsertFailException {
        //return new AdminBean(0, "name", "name","name");
        adminMapper = sqlSession.getMapper(AdminMapper.class);
        int flag = adminMapper.insertAdmin(adminBean);

        if(flag == 0){
            throw new InsertFailException("AdminBean insert에 실패하였습니다.");
        }
    }

}
