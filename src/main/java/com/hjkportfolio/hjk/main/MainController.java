package com.hjkportfolio.hjk.main;

import com.hjkportfolio.hjk.post.Criteria;
import com.hjkportfolio.hjk.update.UpdateService;
import com.hjkportfolio.hjk.update.UpdateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    UpdateService updateService;

    @GetMapping("")
    public String index(Model model){
        List<UpdateVO> updateList = updateService.getUpdateList(new Criteria(1, 4));

        model.addAttribute("updateList", updateList);

        return "index";
    }

}
