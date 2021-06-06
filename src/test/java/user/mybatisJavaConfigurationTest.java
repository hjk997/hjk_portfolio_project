package user;

import com.hjkportfolio.hjk.MyBatisConfig;
import com.hjkportfolio.hjk.user.AdminBean;
import com.hjkportfolio.hjk.user.LoginService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

public class mybatisJavaConfigurationTest {
    @Autowired
    LoginService loginService;

    @Test
    public void 데이터베이스_연결_확인() throws Exception {
        MyBatisConfig myBatisConfig = new MyBatisConfig();
        loginService = myBatisConfig.loginService();
        AdminBean adminBean = new AdminBean(1, "hjk997", "345612", "hjk");
        AdminBean resultAdminBean = loginService.getAdminBean(adminBean);

        Assertions.assertThat(adminBean).isSameAs(resultAdminBean);
    }
}
