package com.hjkportfolio.hjk.mapper;

import com.hjkportfolio.hjk.post.ProjectVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectMapper {
    @Insert("INSERT INTO portfolio.project(project_type, grade_type, title, summary, part, review, link, writer_uid, started_date, ended_date) \n" +
            "VALUES(#{projectType}, #{gradeType}, #{title}, #{summary}, #{part}, #{review}, #{link}, #{writerUid}, #{startedDate}, #{endedDate});")
    public int insertProjectTable(ProjectVO projectVO);

}
