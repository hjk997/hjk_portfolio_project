package com.hjkportfolio.hjk.user;

import com.hjkportfolio.hjk.exception.InsertFailException;
import com.hjkportfolio.hjk.mapper.AdminMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

public class LoginService implements LoginControllerImpl {

    SqlSession sqlSession;

    @Autowired
    AdminMapper adminMapper;

    public LoginService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Optional<AdminVO> login(AdminVO adminVO) {
        try {
            //return new AdminBean(0, "name", "name","name");
            adminMapper = sqlSession.getMapper(AdminMapper.class);
            AdminVO optional  = adminMapper.getAdminBean(adminVO);

            return Optional.ofNullable(optional);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void insert(AdminVO adminVO) throws InsertFailException {
        //return new AdminBean(0, "name", "name","name");
        adminMapper = sqlSession.getMapper(AdminMapper.class);
        int flag = adminMapper.insertAdmin(adminVO);

        if(flag == 0){
            throw new InsertFailException("AdminBean insert에 실패하였습니다.");
        }
    }
}
