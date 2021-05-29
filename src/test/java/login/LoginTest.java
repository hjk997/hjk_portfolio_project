package login;

import com.hjkportfolio.hjk.user.AdminBean;
import com.hjkportfolio.hjk.user.LoginController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LoginTest {

    @Test
    @Transactional
    public void 테스트_데이터_insert(){
        // 1. loginController 객체 생성
        LoginController loginController = new LoginController();

        // 2. tc insert
        AdminBean testAdminBean = new AdminBean(0, "asdef","1234","hjk");
        loginController.insertAdminAccount(testAdminBean);

        // 3. 해당 tc select
        Optional<AdminBean> optional = loginController.getAdminBeanInDatabase(testAdminBean.getAdmin_id());

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

    @Test
    @Transactional
    public void 존재하지_않는_id(){
        // 1. loginController 객체 생성
        LoginController loginController = new LoginController();

        // 2. 아무런 tc select
        AdminBean testAdminBean = new AdminBean(0, "admin", "sdf","sdf");
        Optional<AdminBean> optional = loginController.getAdminBeanInDatabase(testAdminBean.getAdmin_id());

        assertThat(optional).isEmpty();
    }

    @Test
    public void 같은_비밀번호(){

    }

    @Test
    public void 다른_비밀번호(){

    }

    @Test
    public void 웹_세션_생성(){
        // TODO

    }
}
