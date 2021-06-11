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
    public String Update(){
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

}
