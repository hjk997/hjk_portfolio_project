package login;

import com.hjkportfolio.hjk.user.AdminBean;
import com.hjkportfolio.hjk.user.LoginController;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@Rollback(true)
public class LoginTest {

    @Autowired
    private SqlSession session;

    @Test
    @Rollback(true)
    public void 테스트_데이터_insert() throws Exception {
        // 1. loginController 객체 생성
        LoginController loginController = new LoginController();

        // 2. tc insert
        AdminBean testAdminBean = new AdminBean(0, "122dgf","1234","hjk");
        insertAdminAccount(testAdminBean);


        // 3. 해당 tc select
        Method method = loginController.getClass().getDeclaredMethod("getAdminBeanInDatabase", String.class);
        method.setAccessible(true);
        Optional<AdminBean> optional = (Optional<AdminBean>) method.invoke(loginController, testAdminBean.getAdmin_id());

        session.rollback();

        if(optional.isPresent()){
            AdminBean databaseAdminBean = optional.get();
            databaseAdminBean.setUid(0);
            assertThat(testAdminBean)
                    .usingRecursiveComparison()
                    .isEqualTo(databaseAdminBean);

        }else{
            Assertions.fail("id에 대응되는 데이터를 불러오지 못 함");
        }
    }

    @Rollback(true)
    private void insertAdminAccount(AdminBean testAdminBean) throws Exception{
        String resource = "mybatis-config.xml";
        InputStream inputStream;

        inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        session = sqlSessionFactory.openSession(false);
        session.insert("mapper.AdminLogin.insertTestAdminUser",
                testAdminBean);
        session.commit();

    }

    @Test
    @Rollback(true)
    public void 존재하지_않는_id() throws Exception {
        // 1. loginController 객체 생성
        LoginController loginController = new LoginController();

        // 2. db에 존재하지 않는 tc select
        AdminBean testAdminBean = new AdminBean(0, "admin", "sdf","sdf");
        Method method = loginController.getClass().getDeclaredMethod("getAdminBeanInDatabase", String.class);
        method.setAccessible(true);
        Optional<AdminBean> optional = (Optional<AdminBean>) method.invoke(loginController, testAdminBean.getAdmin_id());

        assertThat(optional).isEmpty();
    }

    @Test
    public void 같은_비밀번호() throws Exception {
        LoginController loginController = new LoginController();

        AdminBean adminBean1 = new AdminBean(0, "first", "password", "first");
        AdminBean adminBean2 = new AdminBean(0, "second", "password", "second");

        Method method = loginController.getClass().getDeclaredMethod("isLogin", AdminBean.class, AdminBean.class);
        method.setAccessible(true);

        assertThat((boolean)method.invoke(loginController, adminBean1, adminBean2)).isTrue();

    }

    @Test
    public void 다른_비밀번호() throws Exception{
        LoginController loginController = new LoginController();

        AdminBean adminBean1 = new AdminBean(0, "first", "password1", "first");
        AdminBean adminBean2 = new AdminBean(0, "second", "password2", "second");

        Method method = loginController.getClass().getDeclaredMethod("isLogin", AdminBean.class, AdminBean.class);
        method.setAccessible(true);

        assertThat((boolean)method.invoke(loginController, adminBean1, adminBean2)).isFalse();

    }

}
