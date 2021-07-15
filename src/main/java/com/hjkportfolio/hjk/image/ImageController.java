package com.hjkportfolio.hjk.image;

import org.apache.tomcat.jni.FileInfo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.print.DocFlavor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

@Controller
public class ImageController {

    @GetMapping("images")
    public String image(String path, String name) throws IOException {

        System.out.println("path : ");

        return "/";

    }
}
