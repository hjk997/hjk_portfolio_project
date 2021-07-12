package com.hjkportfolio.hjk.mapper;

import com.hjkportfolio.hjk.post.ImageVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageMapper {

    @Insert("insert INTO image VALUES(#{imageName}, #{imageRealName}, #{projectUid}, #{imageSize}, #{writeDate}, #{path});")
    public int insertSingleImage(ImageVO imageVO);

}
