package com.hjkportfolio.hjk.mapper;

import com.hjkportfolio.hjk.image.ImageVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ImageMapper {

    @Insert("INSERT INTO image VALUES(#{imageName}, #{imageRealName}, #{projectUid}, #{imageSize}, #{writeDate}, #{path}, #{imageOrder});")
    public int insertSingleImage(ImageVO imageVO);

    @Delete("DELETE FROM image WHERE project_uid=#{uid}")
    public int deleteImage(int uid);

    @Select("SELECT * FROM image WHERE project_uid=#{uid}")
    public List<ImageVO> getImageList(int uid);

}
