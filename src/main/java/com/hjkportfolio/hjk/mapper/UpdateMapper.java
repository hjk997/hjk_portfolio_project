package com.hjkportfolio.hjk.mapper;

import com.hjkportfolio.hjk.update.UpdateBean;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface UpdateMapper {
    @Insert("insert into update_table(title, contents, writer_uid) value(#{title}, #{contents}, #{writerUid});")
    public void insertUpdateTable(UpdateBean updateBean);

    @Update("update update_table set title=#{title}, contents=#{contents} where uid=#{uid};")
    public void updateUpdateTable(UpdateBean updateBean);

    @Delete("delete from update_table where uid=#{uid};")
    public void deleteUpdateTable(int uid);

    @Select("SELECT update_table.*,admin.name FROM portfolio.update_table, portfolio.admin where update_table.writer_uid = admin.uid order by write_date desc;")
    public List<UpdateBean> getUpdateTableList();

    @Select("SELECT update_table.*,admin.name FROM portfolio.update_table, portfolio.admin where update_table.writer_uid = admin.uid and update_table.uid=#{uid};")
    public UpdateBean selectUpdatePost(int uid);
}
