package com.hjkportfolio.hjk.update;

import com.hjkportfolio.hjk.exception.InsertFailException;
import com.hjkportfolio.hjk.mapper.AdminMapper;
import com.hjkportfolio.hjk.mapper.UpdateMapper;
import com.hjkportfolio.hjk.user.AdminBean;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public class UpdateController {
    @Autowired
    private SqlSession sqlSession;

    UpdateMapper updateMapper;

    public UpdateController(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        updateMapper = sqlSession.getMapper(UpdateMapper.class);
    }

    public List<UpdateBean> getUpdateList(){
        String resource = "mybatis-config.xml";
        InputStream inputStream;

        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            try (SqlSession session = sqlSessionFactory.openSession()) {
                List<UpdateBean> updateBeanList = session.selectList("mapper.Update.selectUpdate");

                session.close();
                return updateBeanList;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public UpdateBean getUpdatePost(int uid){
        //return new AdminBean(0, "name", "name","name");
        UpdateBean updateBean = updateMapper.selectUpdatePost(uid);
        return updateBean;
    }

    public void insertUpdateTable(UpdateBean updateBean) {
        //return new AdminBean(0, "name", "name","name");
        updateMapper.insertUpdateTable(updateBean);

    }

    public void updateUpdateTable(UpdateBean updateBean) {
        //return new AdminBean(0, "name", "name","name");
        updateMapper.updateUpdateTable(updateBean);

    }

    public void deleteUpdateTable(int uid) {
        //return new AdminBean(0, "name", "name","name");
        updateMapper.deleteUpdateTable(uid);

    }
}
