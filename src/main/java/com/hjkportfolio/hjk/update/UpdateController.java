package com.hjkportfolio.hjk.update;

import com.hjkportfolio.hjk.mapper.UpdateMapper;
import com.hjkportfolio.hjk.post.Criteria;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UpdateController{
    private SqlSession sqlSession;

    @Autowired
    UpdateMapper updateMapper;

    public UpdateController(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<UpdateVO> getUpdateList(Criteria criteria){
        List<UpdateVO> updateList = updateMapper.getUpdateTableList(criteria);
        return updateList;
    }

    public UpdateVO getUpdatePost(int uid){
        //return new AdminBean(0, "name", "name","name");
        UpdateVO updateVO = updateMapper.selectUpdatePost(uid);
        return updateVO;
    }

    public void insertUpdateTable(UpdateVO updateVO) {
        //return new AdminBean(0, "name", "name","name");
        updateMapper.insertUpdateTable(updateVO);

    }

    public void updateUpdateTable(UpdateVO updateVO) {
        //return new AdminBean(0, "name", "name","name");
        updateMapper.updateUpdateTable(updateVO);

    }

    public void deleteUpdateTable(int uid) {
        //return new AdminBean(0, "name", "name","name");
        updateMapper.deleteUpdateTable(uid);

    }

    public int getTotal() {
        return updateMapper.getCntOfUpdatePost();
    }
}
