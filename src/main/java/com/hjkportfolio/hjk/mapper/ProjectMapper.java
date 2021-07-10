package com.hjkportfolio.hjk.mapper;

import com.hjkportfolio.hjk.post.Criteria;
import com.hjkportfolio.hjk.post.ProjectVO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProjectMapper {
    @Insert("INSERT INTO portfolio.project(project_type, grade_type, title, tech_stack, summary, part, review, link, writer_uid, started_date, ended_date) \n" +
            "VALUES(#{projectType}, #{gradeType}, #{title}, #{techStack}, #{summary}, #{part}, #{review}, #{link}, #{writerUid}, #{startedDate}, #{endedDate});")
    @SelectKey(statement = "select last_insert_id();", keyProperty = "uid", before = false, resultType = int.class)
    public int insertProjectTable(ProjectVO projectVO);

    @Update("Update portfolio.project set project_type = #{projectType}, grade_type= #{gradeType}, title= #{title}, tech_stack= #{techStack}, " +
            "summary= #{summary}, part= #{part}, review = #{review}, link = #{link}, writer_uid = #{writerUid}, started_date = #{startedDate}, ended_date = #{endedDate}" +
            "where uid=#{uid};")
    public int updateProjectTable(ProjectVO projectVO);

    @Select("SELECT * FROM portfolio.project ORDER BY uid desc LIMIT #{skip}, #{amount};")
    public List<ProjectVO> getProjectList(Criteria criteria);

    @Select("SELECT * FROM portfolio.project WHERE uid = #{uid};")
    public ProjectVO getProject(int uid);

    @Delete("Delete FROM portfolio.project WHERE uid = #{uid};")
    int deleteProject(int uid);

    @Select("SELECT COUNT(*) FROM portfolio.project;")
    int getTotal();

    @Select("SELECT uid FROM portfolio.project WHERE uid=(select last_insert_id());")
    Optional<Integer> getLastInsertedId();
}
