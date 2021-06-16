package update;

import com.hjkportfolio.hjk.MyBatisConfig;
import com.hjkportfolio.hjk.exception.InsertFailException;
import com.hjkportfolio.hjk.update.UpdateVO;
import com.hjkportfolio.hjk.update.UpdateController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Rollback(true)
public class UpdateTest {

    UpdateController updateController;

    @BeforeEach
    void before() throws Exception {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyBatisConfig.class);
        updateController = applicationContext.getBean("updateController", UpdateController.class);
    }

    @Test
    public void 업데이트_글_가져오기(){

        List<UpdateVO> updateBeanList = updateController.getUpdateList();

        if(updateBeanList == null){
            Assertions.fail("select 결과를 불러오지 못 함");
        }
    }

    @Test
    public void 업데이트_글_작성하기() throws InsertFailException {
        String title = "test_table";
        UpdateVO updateBean1 = new UpdateVO(1, title, new Date(), "contents", 1, "hjk");

        updateController.insertUpdateTable(updateBean1);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void 업데이트_글_수정하기() throws InsertFailException {
        String title = "test_table3";
        UpdateVO updateBean1 = new UpdateVO(6, title, new Date(), "contents1234", 1, "hjk");

        updateController.updateUpdateTable(updateBean1);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void 업데이트_글_삭제하기() throws InsertFailException {

        updateController.deleteUpdateTable(6);
    }

    @Test
    public void 글_가져오기_테스트() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");
        UpdateVO updateBean1 = new UpdateVO(4, "test_table", dateFormat.parse("금 6월 11 03:00:35 2021"), "contents", 1, "hjk");

        UpdateVO updateBean2 = updateController.getUpdatePost(4);
        System.out.println(updateBean2.toString());

        org.junit.jupiter.api.Assertions.assertEquals(updateBean1, updateBean2);

    }

}
