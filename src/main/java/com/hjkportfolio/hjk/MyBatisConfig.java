package com.hjkportfolio.hjk;

import com.hjkportfolio.hjk.image.ImageService;
import com.hjkportfolio.hjk.project.ProjectService;
import com.hjkportfolio.hjk.update.UpdateService;
import com.hjkportfolio.hjk.user.LoginService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.Reader;
import java.util.Properties;

@Configuration
@MapperScan("com.hjkportfolio.hjk.mapper")
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
        return session;
    }

    @Bean
    public DataSource dataSource() throws Exception{
        String resource = "application.properties";
        Properties properties = new Properties();

        Reader reader = Resources.getResourceAsReader(resource);
        properties.load(reader);

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(properties.getProperty("spring.datasource.driver-class-name"));
        dataSourceBuilder.url(properties.getProperty("spring.datasource.url"));
        dataSourceBuilder.username(properties.getProperty("spring.datasource.username"));
        dataSourceBuilder.password(properties.getProperty("spring.datasource.password"));
        return dataSourceBuilder.build();
    }

    @Bean
    public LoginService loginService() throws Exception {
        return new LoginService(sqlSession());
    }

    @Bean
    public UpdateService updateService() throws Exception {
        return new UpdateService(sqlSession());
    }

    @Bean
    public ProjectService projectService() throws Exception {
        return new ProjectService(sqlSession());
    }

    @Bean
    public ImageService imageService() throws Exception {
        return new ImageService();
    }

}
