package com.hjkportfolio.hjk.project;

import com.hjkportfolio.hjk.image.ImageService;
import com.hjkportfolio.hjk.image.ImageVO;
import com.hjkportfolio.hjk.post.Criteria;
import com.hjkportfolio.hjk.post.PageMaker;
import com.hjkportfolio.hjk.post.ProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    ImageService imageService;

    @GetMapping("project")
    public String project(int id, Model model){

        // 1. 프로젝트 게시글 가져오기
        ProjectVO projectVO = projectService.getProject(id);
        model.addAttribute("project", projectVO);

        // 2. 게시글에 물려있는 이미지 가져오기
        List<ImageVO> imageVOList = imageService.getImageList(id);

        // 3. 가져온 이미지를 파일 형태로 리스트에 저장하기
        model.addAttribute("imageList", imageVOList);

        return "project";
    }

    @GetMapping("project-list")
    public String projectList(Model model, Criteria criteria){
        int total = projectService.getTotal();

        // 페이지 값으로 들고온 값이 total보다 크면 페이지를 표시할 수 없다는 문구를 출력함
        if(!criteria.isPageNumValid(total)) {
            // 에러가 발생했을 때는 이미지를, 아닐 때는 표를 출력
            criteria.setPageNum((int) total / 10);
            model.addAttribute("checkCode", 0);
            model.addAttribute("pageMaker", new PageMaker(criteria, projectService.getTotal()));
        }else{
            List<ProjectVO> projectVOList = projectService.getProjectList(criteria);

            model.addAttribute("projectList", projectVOList);
            model.addAttribute("checkCode", 1);
            model.addAttribute("pageMaker", new PageMaker(criteria, projectService.getTotal()));
        }

        return "project-list";
    }

    @GetMapping("write-project")
    public String writeProject(Optional<Integer> id, Model model){
        if(id.isPresent()){
            model.addAttribute("project", projectService.getProject(id.get()));
            model.addAttribute("imageList", imageService.getImageList(id.get()));
        }

        return "write-project";
    }

    @PostMapping("project/update")
    @ResponseBody
    @Transactional
    public String updateProject(@RequestBody List<MultipartFile> imageFiles, ProjectVO projectVO, String[] preImageFiles){
        int order = 1;

        if(projectVO.getUid() == 0){
            // 삽입
            projectService.insertProjectTable(projectVO);
            // return 받은 uid 값으로 image 삽입
            if(imageFiles != null) {
                for (MultipartFile multipartFile : imageFiles) {
                    if (multipartFile.getName().isEmpty() || multipartFile.getName().isBlank())
                        continue;
                    imageService.setImageVO(multipartFile, projectVO.getUid(), order++);
                }
            }

        }else{
            // 수정
            projectService.updateProjectTable(projectVO);

            // 1. 기존에 저장된 이미지 리스트 가져오기
            List<ImageVO> list = imageService.getImageList(projectVO.getUid());

            if(preImageFiles != null) {
                Collections.sort(list);

                for (int i = 0; i < list.size(); i++) {
                    boolean isExist = true;
                    String name = list.get(i).getImageName();

                    for (int j = 0; j < preImageFiles.length; j++) {
                        if (name.equals(preImageFiles[j])) {
                            // 2. 우선 순위 변경
                            imageService.updateImageOrder(name, order++);
                            isExist = false;
                            break;
                        }
                    }

                    if (isExist) {
                        // 3. 서버 폴더에서 이미지 삭제
                        imageService.deleteImageInServerFolder(list.get(i));
                        // 4. DB에서 이미지 삭제
                        imageService.deleteImageWithName(name);
                    }
                }
            } else if(!list.isEmpty()){
                imageService.deleteAllImageInServer(projectVO.getUid());
            }

            if(imageFiles != null) {
                for (MultipartFile multipartFile : imageFiles) {
                    if (multipartFile.getName().isEmpty() || multipartFile.getName().isBlank())
                        continue;
                    imageService.setImageVO(multipartFile, projectVO.getUid(), order++);
                }
            }
        }

        return "redirect:/project-list";
    }

    @GetMapping("project/delete")
    public String deleteProject(int id){

        imageService.deleteAllImageInServer(id);
        projectService.deleteProject(id);

        return "redirect:/project-list";
    }
}
