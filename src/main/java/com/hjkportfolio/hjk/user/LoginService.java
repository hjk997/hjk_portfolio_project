package com.hjkportfolio.hjk.user;

import com.hjkportfolio.hjk.exception.InsertFailException;
import com.hjkportfolio.hjk.mapper.AdminMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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

    public void InsertAdminBean(AdminBean adminBean) throws InsertFailException{
        //return new AdminBean(0, "name", "name","name");
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        int flag = adminMapper.insertAdmin(adminBean);

        if(flag == 0){
            throw new InsertFailException("AdminBean insert에 실패하였습니다.");
        }
    }
}
