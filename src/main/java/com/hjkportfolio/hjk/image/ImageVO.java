package com.hjkportfolio.hjk.image;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ImageVO implements Comparable<ImageVO>{
    String ImageName;
    String imageRealName;
    int projectUid;
    int imageSize;
    Date writeDate;
    String path;
    int imageOrder;

    @Override
    public int compareTo(ImageVO o) {
        return this.imageOrder - o.imageOrder;
    }
}