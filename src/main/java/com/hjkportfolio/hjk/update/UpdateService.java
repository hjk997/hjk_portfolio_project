package com.hjkportfolio.hjk.update;

import com.hjkportfolio.hjk.mapper.UpdateMapper;
import com.hjkportfolio.hjk.post.Criteria;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UpdateService {
    private SqlSession sqlSession;

    @Autowired
    private UpdateMapper updateMapper;

    public UpdateService(SqlSession sqlSession) {
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

    public int insertUpdateTable(UpdateVO updateVO) {
        //return success code
        return updateMapper.insertUpdateTable(updateVO);

    }

    public int updateUpdateTable(UpdateVO updateVO) {
        //return success code
        return updateMapper.updateUpdateTable(updateVO);

    }

    public int deleteUpdateTable(int uid) {
        //return success code
        return updateMapper.deleteUpdateTable(uid);
    }

    public int getTotal() {
        return updateMapper.getCntOfUpdatePost();
    }
}
