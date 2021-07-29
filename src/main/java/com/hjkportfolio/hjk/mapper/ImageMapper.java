package com.hjkportfolio.hjk.mapper;

import com.hjkportfolio.hjk.image.ImageVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ImageMapper {

    @Insert("INSERT INTO image VALUES(#{imageName}, #{imageRealName}, #{projectUid}, #{imageSize}, #{writeDate}, #{path}, #{imageOrder});")
    public int insertSingleImage(ImageVO imageVO);

    @Delete("DELETE FROM image WHERE project_uid=#{uid};")
    public int deleteImageWithUid(@Param("uid") int uid);

    @Delete("DELETE FROM image WHERE image_name=#{name};")
    public int deleteImageWithName(String name);

    @Update("UPDATE image SET image_order=#{order} WHERE image_name=#{name};")
    public int updateImageOrder(@Param("order") int order, @Param("name") String name);

    @Select("SELECT * FROM image WHERE project_uid=#{uid};")
    public List<ImageVO> getImageList(int uid);

    @Select("SELECT * FROM image WHERE image_name=#{name};")
    public ImageVO getImageWithName(@Param("name") String name);

    @Select("SELECT * FROM image WHERE project_uid=#{uid} AND image_order=1")
    public ImageVO getThumbnailImage(@Param("uid") int uid);
}
