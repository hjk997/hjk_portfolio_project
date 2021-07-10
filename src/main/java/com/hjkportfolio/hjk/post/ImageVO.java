package com.hjkportfolio.hjk.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ImageVO {
    String ImageName;
    String imageRealName;
    int projectUid;
    int imageSize;
    Date writeDate;
    String path;
}