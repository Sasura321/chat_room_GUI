package com.github.java.client.dao;

import com.github.java.client.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;

/**
 * Auther： zsm
 * Date： 2019/8/12 9:57
 */
//public class AccountDao extends BaseDao {
//
//    public boolean userRegist(User user) {
//        Connection connection = null;
//        PreparedStatement statement = null;
//        try {
//            connection = getConnection();
//            String sql = "INSERT INTO user(username,password,brief) " + "VALUES(?,?,?)";
//            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            statement.setString(1, user.getUsername());
//            statement.setString(2, DigestUtils.md5Hex(user.getPassword()));
//            statement.setString(2, user.getBrief());
//
//            int rows = statement.executeUpdate();
//            if (rows == 1)
//                return true;
//        } catch (SQLException e) {
//            System.out.println("用户注册失败");
//            e.printStackTrace();
//        } finally {
//            releaseResource(connection,statement,null);
//        }
//
//        return false;
//    }
//
//    public User userLogin(String username, String password) {
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//
//        try {
//            connection = getConnection();
//            String sql = "SELECT * FROM user WHERE username=? AND password=?";
//            statement = connection.prepareStatement(sql);
//            statement.setString(1, username);
//            statement.setString(2, DigestUtils.md5Hex(password));
//
//            resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                User user = getUser(resultSet);
//                return user;
//            }
//        } catch (SQLException e) {
//            System.out.println("用户登录失败");
//            e.printStackTrace();
//        } finally {
//            releaseResource(connection,statement,resultSet);
//        }
//        return null;
//    }
//
//    private User getUser(ResultSet resultSet) throws SQLException{
//        User user = new User();
//        user.setId(resultSet.getInt("id"));
//        user.setUsername(resultSet.getString("username"));
//        user.setPassword(resultSet.getString("password"));
//        user.setBrief(resultSet.getString("brief"));
//        return user;
//    }
//}

public class AccountDao extends BaseDao{
    // 用户注册 insert
    public boolean userReg(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            String sql = "INSERT INTO user(username, password,brief) " +
                    " VALUES(?,?,?) ";
            statement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,user.getUsername());
            statement.setString(2, DigestUtils.md5Hex(user.getPassword()));
            statement.setString(3,user.getBrief());
            int rows = statement.executeUpdate();
            if (rows == 1)
                return true;
        }catch (SQLException e) {
            System.err.println("用户注册失败");
            e.printStackTrace();
        }finally {
            releaseResource(connection,statement,null);
        }
        return false;
    }

    public User userLogin(String userName,String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,userName);
            statement.setString(2,DigestUtils.md5Hex(password));
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = getUser(resultSet);
                return user;
            }
        }catch (SQLException e) {
            System.err.println("用户登录失败");
            e.printStackTrace();
        }finally {
            releaseResource(connection,statement,resultSet);
        }
        return null;
    }

    private User getUser(ResultSet resultSet) throws SQLException{
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setBrief(resultSet.getString("brief"));
        return user;
    }
}
