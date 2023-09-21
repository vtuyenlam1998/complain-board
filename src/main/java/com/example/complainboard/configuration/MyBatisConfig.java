package com.example.complainboard.configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.example.complainboard.mapper")
public class MyBatisConfig {
    @Value("${db.driver_name}")
    private String dbDriverName;
    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.username}")
    private String dbUsername;

    @Value("${db.password}")
    private String dbPassword;


    //    Configure the connection port to the database using DataSource
    @Bean
    public DataSource dataSource() {
//        This method creates a DriverManagerDataSource object, one of the implementations of the DataSource interface.
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

//        This is the configuration of the JDBC driver used for connecting to the MariaDB database.
        dataSource.setDriverClassName(dbDriverName);

//        This is the connection URL to the MariaDB database. It includes information about the server, port, and database name.
        dataSource.setUrl(dbUrl);

//        This is the username used to log into the database.
        dataSource.setUsername(dbUsername);

//        This is the user's password to log into the database.
        dataSource.setPassword(dbPassword);
        return dataSource;
    }
    @Bean
//    This method defines a bean named "sqlSessionFactory" of type SqlSessionFactory. It takes a DataSource as an argument, indicating that this bean depends on a DataSource bean.
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//        Here, an instance of the SqlSessionFactoryBean class is created. This class is part of MyBatis, a Java persistence framework that integrates with databases.
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();

//        This line sets the DataSource for the SqlSessionFactoryBean. It configures the data source that MyBatis will use to interact with the database.
        sessionFactory.setDataSource(dataSource);

//        Finally, the method returns the SqlSessionFactory object created and configured in the previous steps. This SqlSessionFactory will be used to create SQL sessions for interacting with the database.
        return sessionFactory.getObject();
    }

    @Bean
//    This method defines a bean named "transactionManager" of type DataSourceTransactionManager. It takes a DataSource as an argument, indicating that this bean depends on a DataSource bean.
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
//        This line creates a new instance of DataSourceTransactionManager and initializes it with the provided DataSource. The DataSourceTransactionManager manages database transactions, ensuring that they are properly committed or rolled back.
        return new DataSourceTransactionManager(dataSource);
    }
}
