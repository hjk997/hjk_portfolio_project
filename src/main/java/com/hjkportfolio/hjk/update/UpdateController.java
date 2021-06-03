package com.hjkportfolio.hjk.update;

import com.hjkportfolio.hjk.user.AdminBean;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Controller
public class UpdateController {

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

    public int insertUpdateTable(UpdateBean updateBean){
        String resource = "mybatis-config.xml";
        InputStream inputStream;

        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            try (SqlSession session = sqlSessionFactory.openSession()) {
                int flag = session.insert("mapper.Update.insertUpdate", updateBean);

                session.close();
                return flag;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }

    }

}
