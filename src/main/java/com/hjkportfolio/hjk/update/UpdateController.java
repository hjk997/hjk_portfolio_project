package com.hjkportfolio.hjk.update;

import com.hjkportfolio.hjk.post.Criteria;
import com.hjkportfolio.hjk.post.PageMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UpdateController {

    @Autowired
    UpdateService updateService;

    /**
     * 게시글 목록 확인
     *
     * @param model
     * @return
     */
    @GetMapping("update-list")
    public String updateList(Model model, Criteria criteria){
        int total = updateService.getTotal();

        // 페이지 값으로 들고온 값이 total보다 크면 페이지를 표시할 수 없다는 문구를 출력함
        if(!criteria.isPageNumValid(total)){
            // 에러가 발생했을 때는 이미지를, 아닐 때는 표를 출력
            criteria.setPageNum((int)total/10);
            model.addAttribute("checkCode", 0);
            model.addAttribute("pageMaker", new PageMaker(criteria, total));
            return "update-list";
        }else{
            List<UpdateVO> updateVOList = updateService.getUpdateList(criteria);

            model.addAttribute("updateList", updateVOList);
            model.addAttribute("checkCode", 1);
            model.addAttribute("pageMaker", new PageMaker(criteria, total));

            return "update-list";
        }

    }

    /**
     * 게시글 확인
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("update")
    public String loadUpdate(String id, Model model){

        // 해당 게시글 찾아서 Bean 형태로 return 해주기
        UpdateVO updatePost = updateService.getUpdatePost(Integer.parseInt(id));

        model.addAttribute("updatePost", updatePost);

        return "update";
    }

    /**
     * 게시글 작성
     *
     * @return
     */
    @GetMapping("write-update")
    public String loadWriteUpdate(){
        return "write-update";
    }


    @PostMapping("write-update")
    public String insertPost(UpdateVO updateVO, HttpSession httpSession){
        updateVO.setUid(0);
        updateVO.setWriterUid((Integer) httpSession.getAttribute("uid"));
        updateService.insertUpdateTable(updateVO);

        return "redirect:/update-list";
    }

    /**
     * 게시글 수정
     *
     * @param updateVO
     * @param model
     * @return
     */
    @PostMapping("rewrite-update")
    public String loadRewriteUpdate(UpdateVO updateVO, Model model){

        model.addAttribute("updatePost", updateVO);

        return "rewrite-update";
    }

    @PostMapping("rewrite-update/update")
    public String updatePost(UpdateVO updateVO, HttpSession httpSession){

        updateVO.setWriterUid((Integer) httpSession.getAttribute("uid"));
        updateService.updateUpdateTable(updateVO);

        return "redirect:/update-list";
    }

    /**
     * 게시글 삭제
     *
     * @param id
     * @param httpSession
     * @return
     */
    @GetMapping("update/delete")
    public String deletePost(String id, HttpSession httpSession){
        // db에서 해당 uid와 일치하는 글을 가져왔는데 그 글의 writer의 uid가 현재 로그인 된 유저의 uid와 같다면 변경 가능
        updateService.deleteUpdateTable(Integer.parseInt(id));

        return "redirect:/update-list";
    }

}
