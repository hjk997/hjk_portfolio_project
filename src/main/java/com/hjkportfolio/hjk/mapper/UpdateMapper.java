package com.hjkportfolio.hjk.mapper;

import com.hjkportfolio.hjk.post.Criteria;
import com.hjkportfolio.hjk.update.UpdateVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UpdateMapper {
    @Insert("insert into update_table(title, contents, writer_uid, note) value(#{title}, #{contents}, #{writerUid}, #{note});")
    public void insertUpdateTable(UpdateVO updateBean);

    @Update("update update_table set title=#{title}, contents=#{contents}, note=#{note} where uid=#{uid};")
    public void updateUpdateTable(UpdateVO updateBean);

    @Delete("delete from update_table where uid=#{uid};")
    public void deleteUpdateTable(int uid);

    @Select("SELECT update_table.*,admin.name FROM portfolio.update_table, portfolio.admin where update_table.writer_uid = admin.uid order by note desc, write_date desc limit #{skip}, #{amount};")
    public List<UpdateVO> getUpdateTableList(Criteria criteria);

    @Select("SELECT update_table.*,admin.name FROM portfolio.update_table, portfolio.admin where update_table.writer_uid = admin.uid and update_table.uid=#{uid};")
    public UpdateVO selectUpdatePost(int uid);
}
