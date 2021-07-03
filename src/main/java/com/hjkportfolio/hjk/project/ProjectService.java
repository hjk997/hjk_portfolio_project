package com.hjkportfolio.hjk.project;

import com.hjkportfolio.hjk.mapper.ProjectMapper;
import com.hjkportfolio.hjk.post.ProjectVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class ProjectService {
    private SqlSession sqlSession;

    @Autowired
    private ProjectMapper projectMapper;

    public ProjectService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public int insertProjectTable(ProjectVO projectVO){
        return projectMapper.insertProjectTable(projectVO);
    }


}
