package com.github.java.client.service;

import com.github.java.client.dao.AccountDao;
import com.github.java.client.entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Auther： zsm
 * Date： 2019/8/12 11:45
 */
public class userRegiest {

    private JPanel regiestPanel;
    private JTextField userNameTextField;
    private JPasswordField passwordField;
    private JTextField birefTextField;
    private JLabel userNameLable;
    private JLabel passwordLabel;
    private JLabel briefLabel;
    private JButton regButton;

//    private AccountDao accountDao = new AccountDao();
//
//    public userRegiest() {
//        JFrame frame = new JFrame("用户注册");
//        frame.setContentPane(regiestPanel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLocationRelativeTo(null);
//        frame.pack();
//        frame.setVisible(true);
//        // 点击注册按钮,将信息持久化到db中,成功弹出提示框
//        regButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // 获取用户输入的注册信息
//                String userName = userNameTextField.getText();
//                String password = String.valueOf
//                        (passwordField.getPassword());
//                String brief = birefTextField.getText();
//                // 将输入信息包装为User类，保存到数据库中
//                User user = new User();
//                user.setUsername(userName);
//                user.setPassword(password);
//                user.setBrief(brief);
//                // 调用dao对象
//                if (accountDao.userRegist(user)) {
//                    // 返回登录页面
//                    JOptionPane.showMessageDialog(frame,"注册成功!",
//                            "提示信息",JOptionPane.INFORMATION_MESSAGE);
//                    frame.setVisible(false);
//                }else {
//                    // 保留当前注册页面
//                    JOptionPane.showMessageDialog(frame,"注册失败!",
//                            "错误信息",JOptionPane.ERROR_MESSAGE);
//                }
//            }
//        });
//
//    }
//}


    private AccountDao accountDao = new AccountDao();

    public userRegiest() {
        JFrame frame = new JFrame("用户注册");
        frame.setContentPane(regiestPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

        // 点击注册按钮，将信息持久化到 db 中，成功共淡出提示框
        regButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取用户输入的注册信息
                String userName = userNameTextField.getText();
                String password = String.valueOf(passwordField.getPassword());
                String brief = birefTextField.getText();

                // 将输入信息包装为 User 类，保存到数据库中
                User user = new User();
                user.setUsername(userName);
                user.setPassword(password);
                user.setBrief(brief);

                // 调用dao对象
                if (accountDao.userReg(user)) {
                    // 返回登录界面
                    JOptionPane.showMessageDialog(frame,"注册成功","提示信息",JOptionPane.INFORMATION_MESSAGE);
                    frame.setVisible(false);
                } else {
                    // 保留当前注册页面
                    JOptionPane.showMessageDialog(frame,"注册失败",
                            "错误信息",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
