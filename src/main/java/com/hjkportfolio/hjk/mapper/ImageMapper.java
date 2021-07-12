package com.hjkportfolio.hjk.mapper;

import com.hjkportfolio.hjk.post.ImageVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ImageMapper {

    @Insert("insert INTO image VALUES(#{imageName}, #{imageRealName}, #{projectUid}, #{imageSize}, #{writeDate}, #{path});")
    public int insertSingleImage(ImageVO imageVO);

    @Select("select image_name, path from image where project_uid=#{uid}")
    public List<ImageVO> getImageList(int uid);
}
