package com.hjkportfolio.hjk.project;

import com.hjkportfolio.hjk.mapper.ProjectMapper;
import com.hjkportfolio.hjk.post.Criteria;
import com.hjkportfolio.hjk.post.ProjectVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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

    public List<ProjectVO> getProjectList(Criteria criteria){
        return projectMapper.getProjectList(criteria);
    }

    public ProjectVO getProject(int uid) {
        return projectMapper.getProject(uid);
    }

    public int deleteProject(int id) {
        return projectMapper.deleteProject(id);
    }

    public int getTotal() {
        return projectMapper.getTotal();
    }
}
