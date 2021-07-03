package update;

import com.hjkportfolio.hjk.MyBatisConfig;
import com.hjkportfolio.hjk.exception.InsertFailException;
import com.hjkportfolio.hjk.post.Criteria;
import com.hjkportfolio.hjk.update.UpdateVO;
import com.hjkportfolio.hjk.update.UpdateService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

@Transactional
@Rollback(true)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UpdateTest {

    @Autowired
    UpdateService updateService;

    static List<UpdateVO> updateBeanList;

    @BeforeEach
    void before(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyBatisConfig.class);
        updateService = applicationContext.getBean("updateService", UpdateService.class);
    }

    @Test
    @Order(1)
    public void 업데이트_글_가져오기(){

        updateBeanList = updateService.getUpdateList(new Criteria());

        org.junit.jupiter.api.Assertions.assertFalse(updateBeanList.isEmpty());
    }

    @Test
    @Order(2)
    public void 업데이트_글_작성하기() {
        UpdateVO updateBean1 = updateBeanList.get(0);

        updateBean1.setTitle("new title");
        updateBean1.setContents("new contents");

        try{
            int code = updateService.insertUpdateTable(updateBean1);

            org.junit.jupiter.api.Assertions.assertTrue(code > 0);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Test
    @Order(3)
    public void 글_가져오기_테스트() {
        UpdateVO updateBean1 = updateBeanList.get(1);

        UpdateVO updateBean2 = updateService.getUpdatePost(updateBean1.getUid());

        org.junit.jupiter.api.Assertions.assertEquals(updateBean1, updateBean2);

    }

    @Test
    @Transactional
    @Rollback(true)
    @Order(4)
    public void 업데이트_글_수정하기() {
        UpdateVO updateBean1 = updateBeanList.get(1);

        updateBean1.setTitle("new title");
        updateBean1.setContents("new contents");

        int code = updateService.updateUpdateTable(updateBean1);

        org.junit.jupiter.api.Assertions.assertTrue(code > 0);
    }

    @Test
    @Transactional
    @Rollback(true)
    @Order(5)
    public void 업데이트_글_삭제하기() {
        try{
            UpdateVO updateBean1 = updateBeanList.get(2);
            int code = updateService.deleteUpdateTable(updateBean1.getUid());

            org.junit.jupiter.api.Assertions.assertTrue(code > 0);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    @Rollback(true)
    @Order(5)
    public void 업데이트_글_삭제_실패() {
        try{
            int code = updateService.deleteUpdateTable(35248);

            org.junit.jupiter.api.Assertions.assertFalse(code > 0);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void 페이징_불러오기(){
        updateBeanList = updateService.getUpdateList(new Criteria(1, 10));
        org.junit.jupiter.api.Assertions.assertFalse(updateBeanList.isEmpty());
    }

    @Test
    public void 페이징_큰_amount값_불러오기(){
        updateBeanList = updateService.getUpdateList(new Criteria(1, 25000));
        org.junit.jupiter.api.Assertions.assertFalse(updateBeanList.isEmpty());
    }

    @Test
    public void 페이징_범위_밖의_값_불러오기(){
        updateBeanList = updateService.getUpdateList(new Criteria(175480, 0));
        org.junit.jupiter.api.Assertions.assertTrue(updateBeanList.isEmpty());
    }

}
