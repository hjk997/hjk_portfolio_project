package user;

import com.hjkportfolio.hjk.user.AdminBean;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

public class testDataSource {

    @Test
    public void testDS() throws Exception{
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            AdminBean user = session.selectOne("mapper.UserMapper.selectUser", 1);
            if(user==null){
                System.out.println("fail");
            }else {
                System.out.println(user.getAdmin_id());
                System.out.println(user.getName());
            }
        }finally {
            inputStream.close();
        }
    }

    @Test
    public void testAdminLogin() throws Exception{
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            AdminBean user = session.selectOne("mapper.UserMapper.selectUser", 1);
            if(user==null){
                System.out.println("fail");
            }else {
                System.out.println(user.getAdmin_id());
                System.out.println(user.getName());
            }
        }finally {
            inputStream.close();
        }
    }

}