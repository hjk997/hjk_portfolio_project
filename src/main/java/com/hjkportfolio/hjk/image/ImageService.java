package com.hjkportfolio.hjk.image;

import com.hjkportfolio.hjk.mapper.ImageMapper;
import com.hjkportfolio.hjk.post.ImageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ImageService {

    @Autowired
    ImageMapper imageMapper;

    public int insertSingleImage(ImageVO imageVO){
        return imageMapper.insertSingleImage(imageVO);
    }

    public int setImageVO(MultipartFile multipartFile, int uid) {
        try{
            // 1. 파일 이름 설정
            // 파일 이름을 업로드 한 날짜로 바꾸어서 저장할 것이다
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            String current_date = simpleDateFormat.format(new Date());

            // 2. 파일 경로 설정
            // 프로젝트 폴더에 저장하기 위해 절대경로를 설정 (Window 의 Tomcat 은 Temp 파일을 이용한다)
            String absolutePath = new File("").getAbsolutePath() + "\\";

            // 경로를 지정하고 그곳에다가 저장할 심산이다
            String path = "images/" + current_date;
            System.out.println("path : " + absolutePath + path);
            File file = new File(path);
            // 저장할 위치의 디렉토리가 존지하지 않을 경우
            if (!file.exists()) {
                // mkdir() 함수와 다른 점은 상위 디렉토리가 존재하지 않을 때 그것까지 생성
                file.mkdirs();
            }

            // 3. 파일 첨부
            String contentType = multipartFile.getContentType();
            String originalFileExtension;
            String fileName = multipartFile.getOriginalFilename();
            fileName = fileName.substring(0, fileName.length() - 4);
            // 확장자 명이 없으면 이 파일은 잘 못 된 것이다
            if (ObjectUtils.isEmpty(contentType)) {
                return 0;
            }

            if (contentType.contains("image/jpeg")) {
                originalFileExtension = ".jpg";
            } else if (contentType.contains("image/png")) {
                originalFileExtension = ".png";
            } else if (contentType.contains("image/gif")) {
                originalFileExtension = ".gif";
            } else {
                return 0;
            }

            String new_file_name = fileName + Long.toString(System.nanoTime()) + originalFileExtension;

            ImageVO imageVO = new ImageVO(new_file_name, multipartFile.getOriginalFilename(), uid, (int) multipartFile.getSize(), new Date(), path);

            int code = insertSingleImage(imageVO);

            // 저장된 파일로 변경하여 이를 보여주기 위함
            file = new File(absolutePath + path + "/" + new_file_name);
            multipartFile.transferTo(file);

            // 4. return
            return code;
        } catch (IOException e) {
            e.printStackTrace();

            return 0;
        }
    }

}
