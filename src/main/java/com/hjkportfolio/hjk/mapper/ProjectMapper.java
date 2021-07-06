package com.hjkportfolio.hjk.mapper;

import com.hjkportfolio.hjk.post.Criteria;
import com.hjkportfolio.hjk.post.ProjectVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProjectMapper {
    @Insert("INSERT INTO portfolio.project(project_type, grade_type, title, summary, part, review, link, writer_uid, started_date, ended_date) \n" +
            "VALUES(#{projectType}, #{gradeType}, #{title}, #{summary}, #{part}, #{review}, #{link}, #{writerUid}, #{startedDate}, #{endedDate});")
    public int insertProjectTable(ProjectVO projectVO);

    @Select("SELECT * FROM portfolio.project LIMIT #{skip}, #{amount};")
    public List<ProjectVO> getProjectList(Criteria criteria);

    @Select("SELECT * FROM portfolio.project WHERE uid = #{uid};")
    public ProjectVO getProject(int uid);

    @Delete("Delete FROM portfolio.project WHERE uid = #{uid};")
    int deleteProject(int uid);

    @Select("SELECT COUNT(*) FROM portfolio.project;")
    int getTotal();
}
