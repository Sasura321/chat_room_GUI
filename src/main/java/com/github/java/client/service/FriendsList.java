package com.github.java.client.service;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Auther： zsm
 * Date： 2019/8/13 12:04
 * Description：
 */
public class FriendsList {

    private JPanel friendsPanel;
    private JLabel friebdsLabel;
    private JScrollPane friendScrollPane;
    private JLabel groupLable;
    private JScrollPane groupScrollPane;
    private JButton groupButton;
    private JFrame frame;

    private String userName;
    private Set<String> users;
    private Connect2Server connect2Server;

    private Map<String,PrivateChatGUI> privateChatGUIList = new ConcurrentHashMap<>();

    /*
    * 好友列表后台任务，不断监听服务器发来的消息
    * 好友上线消息、用户私聊、群聊
    */
    private class DaemonTask implements Runnable {
        private Scanner in = new Scanner(connect2Server.getIn());

        @Override
        public void run() {
            while (true) {
                // 收到服务器发来的消息
                if (in.hasNextLine()) {
                    // 此时服务器发来的是一个json字符串

                    // 用户发来私聊消息

                }
            }
        }
    }

    /*
    * 标签点击事件
    */
    private class PrivateLabelAction implements MouseListener {

        // 鼠标点击事件
        @Override
        public void mouseClicked(MouseEvent e) {
            //
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public FriendsList(String userName, Set<String> users, Connect2Server connect2Server) {
        this.userName = userName;
        this.users = users;
        this.connect2Server = connect2Server;
        frame = new JFrame(userName);
        frame.setContentPane(friendsPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,300);
        frame.setLocationRelativeTo(null);
        // frame.pack();
        frame.setVisible(true);
        loadUsers();

        // 启动后台线程来不断监听服务器发来的消息
        Thread daemonThread = new Thread(new DaemonTask());
        daemonThread.setDaemon(true);
        daemonThread.start();

        // 创建群组

    }

    // 加载所有在线的用户信息
    public void loadUsers() {
        JLabel[] userLabels = new JLabel [users.size()];
        JPanel friends = new JPanel();
        friends.setLayout(new BoxLayout(friends,BoxLayout.Y_AXIS));
        // set遍历
        Iterator<String> iterator = users.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            String userName = iterator.next();
            userLabels[i] = new JLabel(userName);
            userLabels[i] = new JLabel(userName);
            // todo
            // 添加标签的点击事件

            friends.add(userLabels[i]);
            i++;
        }
        friendScrollPane.setViewportView(friends);
        // 设置滚动条垂直滚动
        friendScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        friends.revalidate();
        friendScrollPane.revalidate();
    }
}
