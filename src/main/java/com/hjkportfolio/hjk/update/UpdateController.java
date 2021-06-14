package com.hjkportfolio.hjk.update;

import com.hjkportfolio.hjk.mapper.UpdateMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UpdateController {
    private SqlSession sqlSession;

    @Autowired
    UpdateMapper updateMapper;

    public UpdateController(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<UpdateBean> getUpdateList(){
        List<UpdateBean> updateBeanList = updateMapper.getUpdateTableList();
        return updateBeanList;
    }

    public UpdateBean getUpdatePost(int uid){
        //return new AdminBean(0, "name", "name","name");
        UpdateBean updateBean = updateMapper.selectUpdatePost(uid);
        return updateBean;
    }

    public void insertUpdateTable(UpdateBean updateBean) {
        //return new AdminBean(0, "name", "name","name");
        updateMapper.insertUpdateTable(updateBean);

    }

    public void updateUpdateTable(UpdateBean updateBean) {
        //return new AdminBean(0, "name", "name","name");
        updateMapper.updateUpdateTable(updateBean);

    }

    public void deleteUpdateTable(int uid) {
        //return new AdminBean(0, "name", "name","name");
        updateMapper.deleteUpdateTable(uid);

    }
}
