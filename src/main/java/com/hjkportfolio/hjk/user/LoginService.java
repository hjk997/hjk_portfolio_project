package com.hjkportfolio.hjk.user;

import com.hjkportfolio.hjk.mapper.AdminMapper;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private SqlSession sqlSession;

    public LoginService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public AdminBean getAdminBean(AdminBean adminBean){
        //return new AdminBean(0, "name", "name","name");
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        AdminBean adminBean1 = adminMapper.getAdminBean(adminBean);
        return adminBean1;
    }
}
