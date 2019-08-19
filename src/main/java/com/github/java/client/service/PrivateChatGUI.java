package com.github.java.client.service;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.PrintStream;

/**
 * Auther： zsm
 * Date： 2019/8/19 9:50
 * Description：
 */
public class PrivateChatGUI {

    private JTextArea readFromServer;
    private JPanel privateChatPanel;
    private JTextField send2Server;

    private String friendName;
    private String myName;
    private Connect2Server connect2Server;
    private JFrame frame;
    protected PrintStream out;

    public PrivateChatGUI(String friendName, String myName, Connect2Server connect2Server) {
        JFrame frame = new JFrame("与"+friendName+"私聊中...");
        frame.setContentPane(privateChatPanel);

        // 设置窗口关闭的操作，将其设置为隐藏
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        //frame.pack();
        frame.setVisible(true);

        // 捕捉输入框的键盘输入
        send2Server.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

            }
        });
    }
}
