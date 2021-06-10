package com.hjkportfolio.hjk;

import com.hjkportfolio.hjk.mapper.AdminMapper;
import com.hjkportfolio.hjk.mapper.UpdateMapper;
import com.hjkportfolio.hjk.update.UpdateController;
import com.hjkportfolio.hjk.user.LoginService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MyBatisConfig {
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSession() throws Exception {
        SqlSessionTemplate session = new SqlSessionTemplate(sqlSessionFactory());
        session.getConfiguration().addMapper(AdminMapper.class);
        session.getConfiguration().addMapper(UpdateMapper.class);
        return session;
    }

    @Bean
    public DataSource dataSource() throws Exception{
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/portfolio?characterEncoding=UTF-8&serverTimezone=UTC");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("3456");
        return dataSourceBuilder.build();
    }

    @Bean
    public LoginService loginService() throws Exception {
        return new LoginService(sqlSession());
    }

    @Bean
    public UpdateController updateController() throws Exception {
        return new UpdateController(sqlSession());
    }


}
