package com.hjkportfolio.hjk.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ImageBean {
    int uid;
    int project_uid;
    String uuid;
    String imageRealName;
    int imageSize;
    Date updateDate;

}