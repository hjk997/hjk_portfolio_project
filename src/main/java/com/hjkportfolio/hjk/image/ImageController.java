package com.hjkportfolio.hjk.image;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class ImageController {

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
}
