package com.github.java.client.dao;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.github.java.client.entity.User;
import com.github.java.util.CommUtils;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * Auther： zsm
 * Date： 2019/8/12 11:22
 */
public class BaseDaoTest {
    @Test
    public void insert() {

//        User user = new User();
//        user.setUsername("中");
//        user.setPassword("123456");
//        user.setBrief("个性签名");
//        System.out.println(user);

        Properties properties = CommUtils.loadProperties("datasource.properties");
        DataSource dataSource = null;
        try {
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("insert into user(username, password, brief) values(?,?,?)");
            statement.setString(1,"张三");
            statement.setString(2,"123456");
            statement.setString(3,"个性签名");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}