package project;

import com.hjkportfolio.hjk.MyBatisConfig;
import com.hjkportfolio.hjk.post.ProjectVO;
import com.hjkportfolio.hjk.project.ProjectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional
@Rollback(true)
public class ProjectTest {

    ProjectService projectService;

    @BeforeEach
    void before() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyBatisConfig.class);
        projectService = applicationContext.getBean("projectService", ProjectService.class);
    }

    @Test
    public void 프로젝트_게시글_삽입(){
        ProjectVO projectVO = new ProjectVO(0, 0,1,"title","summary", "part","review","link", 1, new Date(), new Date());

        int code = projectService.insertProjectTable(projectVO);

        Assertions.assertTrue(code > 0);
    }

}
