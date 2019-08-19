package com.github.java.client.dao;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.github.java.util.CommUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 封装基础dao操作,获取数据源、连接、关闭资源等
 * Auther： zsm
 * Date： 2019/8/12 9:50
 */
public class BaseDao {

    private static DruidDataSource dataSource;

    static {
        Properties properties = CommUtils.loadProperties("datasource.properties");
        try {
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            System.out.println("数据加载失败");
            e.printStackTrace();
        }
    }

    protected DruidPooledConnection getConnection() {
        try {
            return (DruidPooledConnection) dataSource.getPooledConnection();
        } catch (SQLException e) {
            System.out.println("数据源连接获取失败");
            e.printStackTrace();
        }
        return null;
    }

    protected void releaseResource(Connection connection,
                         PreparedStatement statement,
                         ResultSet resultSet) {

        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
