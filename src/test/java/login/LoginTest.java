package login;

import com.hjkportfolio.hjk.MyBatisConfig;
import com.hjkportfolio.hjk.user.AdminBean;
import com.hjkportfolio.hjk.user.LoginController;
import com.hjkportfolio.hjk.user.LoginService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@Rollback(true)
public class LoginTest {

    @Test
    @Rollback(true)
    @Transactional
    public void 테스트_데이터_insert() throws Exception {
        // 1. loginController 객체 생성
        LoginController loginController = new LoginController();

        // 2. tc insert
        AdminBean testAdminBean = new AdminBean(0, "1997","1324","hjk");
        insertAdminAccount(testAdminBean);

        // 3. 해당 tc select
        Method method = loginController.getClass().getDeclaredMethod("getAdminBeanInDatabase", AdminBean.class);
        method.setAccessible(true);
        Optional<AdminBean> optional = (Optional<AdminBean>) method.invoke(loginController, testAdminBean);

        if(!optional.isPresent()){
            Assertions.fail("id에 대응되는 데이터를 불러오지 못 함");
        }
    }

    @Test
    public void 존재하지_않는_id() throws Exception {
        // 1. loginController 객체 생성
        LoginController loginController = new LoginController();

        // 2. db에 존재하지 않는 tc select
        AdminBean testAdminBean = new AdminBean(0, "admin", "sdf","sdf");
        Method method = loginController.getClass().getDeclaredMethod("getAdminBeanInDatabase", AdminBean.class);
        method.setAccessible(true);
        Optional<AdminBean> optional = (Optional<AdminBean>) method.invoke(loginController, testAdminBean);

        assertThat(optional).isEmpty();
    }

    private void insertAdminAccount(AdminBean testAdminBean) throws Exception{
        try {
            ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyBatisConfig.class);
            LoginService loginService = applicationContext.getBean("loginService", LoginService.class);

            loginService.InsertAdminBean(testAdminBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
