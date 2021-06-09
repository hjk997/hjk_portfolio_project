package user;

import com.hjkportfolio.hjk.MyBatisConfig;
import com.hjkportfolio.hjk.user.AdminBean;
import com.hjkportfolio.hjk.user.LoginService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Rollback(true)
public class mybatisJavaConfigurationTest {
    LoginService loginService;
    MyBatisConfig myBatisConfig;

    @BeforeEach
    void before() throws Exception {
        myBatisConfig = new MyBatisConfig();
        loginService = myBatisConfig.loginService();
    }

    @Test
    public void 데이터베이스_연결_확인() throws Exception {
        AdminBean adminBean = new AdminBean(1, "hjk997", "345612", "hjk");
        AdminBean resultAdminBean = loginService.getAdminBean(adminBean);

        Assertions.assertThat(adminBean.getAdmin_id()).isEqualTo(resultAdminBean.getAdmin_id());
    }

    @Test
    public void 데이터베이스_insert_확인() throws Exception {
        AdminBean adminBean = new AdminBean(1, "test4", "345612", "test");
        loginService.InsertAdminBean(adminBean);
    }

}
