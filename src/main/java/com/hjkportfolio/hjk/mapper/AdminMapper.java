package com.hjkportfolio.hjk.mapper;

import com.hjkportfolio.hjk.user.AdminBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

public interface AdminMapper {
    @Select("select * from admin WHERE admin_id=#{admin_id} and password = hex(AES_ENCRYPT(#{password},SHA2('key', 256)));")
    AdminBean getAdminBean(@Param("admin_id") String admin_id, @Param("password") String password);

    @Insert("insert into admin (admin_id,password,name) values (#{admin_id},hex(AES_ENCRYPT(#{password},SHA2('key', 256))),#{name});")
    int insertAdmin(@Param("admin_id") String admin_id, @Param("password") String password, @Param("name") String name);
}
