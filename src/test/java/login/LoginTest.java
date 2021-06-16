package login;

import com.hjkportfolio.hjk.MyBatisConfig;
import com.hjkportfolio.hjk.user.AdminVO;
import com.hjkportfolio.hjk.user.LoginController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@Rollback(true)
public class LoginTest {

    LoginController loginController;

    @BeforeEach
    void before(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyBatisConfig.class);
        loginController = applicationContext.getBean("loginController", LoginController.class);
    }

    @Test
    @Rollback(true)
    @Transactional
    public void 테스트_데이터_insert() throws Exception {
        // 2. tc insert
        AdminVO testAdminVO = new AdminVO(0, "1997","1324","hjk");
        insertAdminAccount(testAdminVO);

        // 3. 해당 tc select
        Optional<AdminVO> optional = loginController.login(testAdminVO);

        if(!optional.isPresent()){
            Assertions.fail("id에 대응되는 데이터를 불러오지 못 함");
        }
    }

    @Test
    public void 존재하지_않는_id() throws Exception {
        // 2. db에 존재하지 않는 tc select
        AdminVO testAdminVO = new AdminVO(0, "admin", "sdf","sdf");
        Optional<AdminVO> optional = loginController.login(testAdminVO);

        assertThat(optional).isEmpty();
    }

    private void insertAdminAccount(AdminVO testAdminVO) throws Exception{
        try {
            loginController.insert(testAdminVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
