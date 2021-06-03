package update;

import com.hjkportfolio.hjk.update.UpdateBean;
import com.hjkportfolio.hjk.update.UpdateController;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Transactional
@Rollback(true)
public class UpdateTest {

    @Test
    public void 업데이트_글_가져오기(){
        UpdateController updateController = new UpdateController();

        List<UpdateBean> updateBeanList = updateController.getUpdateList();

        if(updateBeanList == null){
            Assertions.fail("select 결과를 불러오지 못 함");
        }
        System.out.println(updateBeanList.get(0).toString());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void 업데이트_글_작성하기(){
        UpdateController updateController = new UpdateController();
        String title = "title";
        UpdateBean updateBean1 = new UpdateBean(0, title, new Date(), "contents", 1, "hjk");

        int flag = updateController.insertUpdateTable(updateBean1);

        if(flag == 0)
            Assertions.fail("insert 실패");

        UpdateBean updateBean2 = getUpdateBean(title);

        if(updateBean2.getName() != updateBean1.getName() || updateBean2.getContents() != updateBean2.getContents()){
            Assertions.fail("insert가 제대로 수행되지 않음");
        }


    }

    public UpdateBean getUpdateBean(String title){
        String resource = "mybatis-config.xml";
        InputStream inputStream;

        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            try (SqlSession session = sqlSessionFactory.openSession()) {
                UpdateBean updateBean = session.selectOne("mapper.Update.selectOneUpdate", title);

                session.close();
                return updateBean;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }



}
