package com.hjkportfolio.hjk.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

@Controller
public class ImageController {
    @Autowired
    ImageService imageService;

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    @GetMapping("images")
    public @ResponseBody void image(String path, String name, HttpServletResponse response) throws IOException {
        // 1. 경로 설정
        StringBuilder sb = new StringBuilder();
        String filePath = sb.append(new File("").getAbsolutePath()).append("\\").
                append("images\\").append(path).append("\\").append(name).toString();

        // 2. 출력하기
        OutputStream outputStream = response.getOutputStream();
        FileInputStream fileInputStream = new FileInputStream(filePath);
        FileCopyUtils.copy(fileInputStream, outputStream);

        outputStream.flush();
    }

    @GetMapping("thumbnail/images")
    public @ResponseBody void thumbnailImage(int id, HttpServletResponse response) throws IOException {

        // 1. 경로 설정
        ImageVO imageVO = imageService.getThumbnailImage(id);

        if(imageVO == null){
            String filePath = "C:\\Users\\unsky\\Documents\\hjkPortfolio\\hjk\\src\\main\\resources\\static\\assets\\img\\no-image.png";

            // 2. 출력하기
            OutputStream outputStream = response.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream(filePath);
            FileCopyUtils.copy(fileInputStream, outputStream);

            outputStream.flush();
        }else{
            StringBuilder sb = new StringBuilder();
            String filePath = sb.append(new File("").getAbsolutePath()).append("\\").
                    append("images\\").append(simpleDateFormat.format(imageVO.getWriteDate())).append("\\").append(imageVO.getImageName()).toString();

            // 2. 출력하기
            OutputStream outputStream = response.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream(filePath);
            FileCopyUtils.copy(fileInputStream, outputStream);

            outputStream.flush();
        }
    }
}
