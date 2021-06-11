package user;

import com.hjkportfolio.hjk.MyBatisConfig;
import com.hjkportfolio.hjk.user.AdminBean;
import com.hjkportfolio.hjk.user.LoginController;
import com.hjkportfolio.hjk.user.LoginService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Rollback(true)
public class mybatisJavaConfigurationTest {
    LoginController loginController;

    @BeforeEach
    void before() throws Exception {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyBatisConfig.class);
        loginController = applicationContext.getBean("loginController", LoginController.class);
    }

    @Test
    public void 데이터베이스_연결_확인() throws Exception {
        AdminBean adminBean = new AdminBean(1, "hjk997", "345612", "hjk");
        Optional<AdminBean> resultAdminBean = loginController.getAdminBeanInDatabase(adminBean);

        if(resultAdminBean.isEmpty())
            Assertions.fail("데이터를 가져오지 못 함");

        Assertions.assertThat(adminBean.getAdmin_id()).isEqualTo(resultAdminBean.get().getAdmin_id());
    }

    @Test
    public void 데이터베이스_insert_확인() throws Exception {
        AdminBean adminBean = new AdminBean(1, "test4", "345612", "test");
        loginController.InsertAdminBean(adminBean);
    }

}
