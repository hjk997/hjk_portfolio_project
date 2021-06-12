package com.hjkportfolio.hjk.mapper;

import com.hjkportfolio.hjk.user.AdminBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {
    @Select("select * from admin WHERE admin_id=#{admin_id} and password = hex(AES_ENCRYPT(#{password},SHA2('key', 256)));")
    AdminBean getAdminBean(AdminBean adminBean);

    @Insert("insert into admin (admin_id,password,name) values (#{admin_id},hex(AES_ENCRYPT(#{password},SHA2('key', 256))),#{name});")
    int insertAdmin(AdminBean adminBean);
}
