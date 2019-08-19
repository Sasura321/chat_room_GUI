package com.github.java.client.dao;

import com.github.java.client.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;

/**
 * Auther： zsm
 * Date： 2019/8/12 17:40
 */
public class AccountDaoTest {
    private AccountDao accountDao = new AccountDao();

    @Test
    public void userReg() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("123");
        user.setBrief("帅");
        boolean flag = accountDao.userReg(user);
        Assert.assertTrue(flag);
    }

    @Test
    public void userLogin() {
        String userName = "test";
        String password = "123";
        User user = accountDao.userLogin(userName,password);
        System.out.println(user);
        Assert.assertNotNull(user);
    }
}