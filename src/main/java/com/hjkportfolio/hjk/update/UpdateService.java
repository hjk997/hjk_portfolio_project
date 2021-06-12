package com.hjkportfolio.hjk.update;

import com.hjkportfolio.hjk.MyBatisConfig;
import org.apache.ibatis.annotations.Update;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class UpdateService {

    @GetMapping("update-list")
    public String updateList(Model model){

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyBatisConfig.class);
        UpdateController updateController = applicationContext.getBean("updateController", UpdateController.class);
        List<UpdateBean> updateBeanList = updateController.getUpdateList();

        model.addAttribute("updateBeanList", updateBeanList);

        return "update-list";
    }

    @GetMapping("write-update")
    public String writeUpdate(){
        return "write-update";
    }

    @GetMapping("update")
    public String Update(String id, Model model){

        // 해당 게시글 찾아서 Bean 형태로 return 해주기

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyBatisConfig.class);
        UpdateController updateController = applicationContext.getBean("updateController", UpdateController.class);
        UpdateBean updatePost = updateController.getUpdatePost(Integer.parseInt(id));

        model.addAttribute("updatePost", updatePost);

        return "update";
    }

    @PostMapping("update/post/write")
    public String writePost(UpdateBean updateBean, HttpSession httpSession){

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyBatisConfig.class);
        UpdateController updateController = applicationContext.getBean("updateController", UpdateController.class);

        //UpdateBean updateBean = new UpdateBean(0, title, new Date(), contents, (Integer) httpSession.getAttribute("uid"), String.valueOf(httpSession.getAttribute("name")));

        updateBean.setUid(0);
        updateBean.setWriterUid((Integer) httpSession.getAttribute("uid"));
        updateController.insertUpdateTable(updateBean);

        return "redirect:/update-list";
    }

    @PostMapping("update/post/update")
    public String updatePost(UpdateBean updateBean, HttpSession httpSession){

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyBatisConfig.class);
        UpdateController updateController = applicationContext.getBean("updateController", UpdateController.class);

        //UpdateBean updateBean = new UpdateBean(0, title, new Date(), contents, (Integer) httpSession.getAttribute("uid"), String.valueOf(httpSession.getAttribute("name")));

        updateBean.setUid(0);
        updateBean.setWriterUid((Integer) httpSession.getAttribute("uid"));
        updateController.insertUpdateTable(updateBean);

        return "redirect:/update-list";
    }

    @GetMapping("update/post/delete")
    public String deletePost(String id, HttpSession httpSession){
        // db에서 해당 uid와 일치하는 글을 가져왔는데 그 글의 writer의 uid가 현재 로그인 된 유저의 uid와 같다면 변경 가능
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyBatisConfig.class);
        UpdateController updateController = applicationContext.getBean("updateController", UpdateController.class);

        updateController.deleteUpdateTable(Integer.parseInt(id));

        return "redirect:/update-list";
    }

}
