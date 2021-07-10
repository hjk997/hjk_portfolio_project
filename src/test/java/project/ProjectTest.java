package project;

import com.hjkportfolio.hjk.MyBatisConfig;
import com.hjkportfolio.hjk.post.Criteria;
import com.hjkportfolio.hjk.post.ProjectVO;
import com.hjkportfolio.hjk.project.ProjectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
@Rollback(true)
public class ProjectTest {

    ProjectService projectService;
    List<ProjectVO> list;

    @BeforeEach
    void before() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyBatisConfig.class);
        projectService = applicationContext.getBean("projectService", ProjectService.class);

        list = projectService.getProjectList(new Criteria());
    }

    @Test
    public void 프로젝트_리스트_가져오기_확인(){
        Assertions.assertFalse(list.isEmpty());
    }

    @Test
    @Disabled
    public void 프로젝트_테스트_게시글_생성(){
        for(int i = 0 ; i < 100 ; i++){
            ProjectVO projectVO = new ProjectVO(0, 0,1,"title"+i, "techStack","summary"+i, "part","review","link", 1, new Date(), new Date());

            int code = projectService.insertProjectTable(projectVO);
            Assertions.assertTrue(code > 0);
        }
    }

    @Test
    public void 프로젝트_게시글_삽입(){
        ProjectVO projectVO = new ProjectVO(0, 0,1,"title","techStack","summary", "part","review","link", 1, new Date(), new Date());

        int code = projectService.insertProjectTable(projectVO);

        Assertions.assertTrue(code > 0 && projectVO.getUid() != 0);
    }

    @Test
    @Disabled
    public void projectVO_확인(){
        if(!list.isEmpty()) {
            ProjectVO projectVO = list.get(0);

            System.out.println(projectVO.toString());
        }
    }

    @Test
    public void 프로젝트_삭제(){
        int code = projectService.deleteProject(list.get(1).getUid());

        Assertions.assertTrue(code > 0);
    }

    @Test
    public void 프로젝트_게시글_수정(){
        ProjectVO projectVO = list.get(2);

        projectVO.setProjectType(2);
        projectVO.setTitle("changed title");
        projectVO.setReview("changed review");

        int code = projectService.updateProjectTable(projectVO);

        Assertions.assertTrue(code > 0);
    }
}
