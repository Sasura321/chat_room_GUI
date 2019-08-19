package com.github.java.server;

import com.github.java.util.CommUtils;
import com.github.java.vo.MessageVo;
import sun.plugin2.message.Message;
import sun.reflect.generics.scope.Scope;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Auther： zsm
 * Date： 2019/8/13 10:40
 * Description：服务器
 */
public class MultiThreadServer {
    private static final String IP;
    private static final int PORT;

    // 缓存当前服务器所有在线的客户端信息
    private static Map<String, Socket> clients = new ConcurrentHashMap<>();

    static {
        Properties pros = CommUtils.loadProperties("socket.properties");
        IP = pros.getProperty("address");
        PORT = Integer.parseInt(pros.getProperty("port"));
    }

    private static class ExecuteClient implements Runnable {
        private Socket client;
        private Scanner in;
        private PrintStream out;

        public ExecuteClient(Socket client) {
            this.client = client;
            try {
                this.in = new Scanner(client.getInputStream());
                this.out = new PrintStream(client.getOutputStream(),true,"UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (true) {
                if (in.hasNextLine()) {
                    String jsonStrFromClient = in.nextLine();
                    MessageVo msgFromClient = (MessageVo) CommUtils.json2Object(jsonStrFromClient, MessageVo.class);

                    // 新用户注册到服务端
                    if (msgFromClient.getType().equals("1")) {
                        String userName = msgFromClient.getContent();

                        // 将当前在线的所有用户名发回客户端
                        MessageVo msg2Client = new MessageVo();
                        msg2Client.setType("1");
                        msg2Client.setContent(CommUtils.object2Json(clients.keySet()));
                        out.println(CommUtils.object2Json(msg2Client));

                        // 将新上线的用户信息发回给当前已在线的所有用户
                        sendUserLogin("newLogin:"+userName);

                        // 将当前新用户注册到服务端缓存
                        clients.put(userName,client);
                        System.out.println(userName+"上线了!");
                        System.out.println("当前聊天室共有"+ clients.size()+"人");
                    }
                }
            }
        }

        /**
         * 向所有在线用户发送新用户上线信息
         * @param msg
         */
        private void sendUserLogin(String msg) {
            for (Map.Entry<String,Socket> entry: clients.entrySet()) {
                Socket socket = entry.getValue();
                try {
                    PrintStream out = new PrintStream(socket.getOutputStream(), true,"UTF-8");
                    out.println(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        ExecutorService executors = Executors.newFixedThreadPool(50);

        for (int i = 0; i < 50; i++) {
            System.out.println("等待客户端连接...");
            Socket client = serverSocket.accept();
            System.out.println("有新的连接,端口号为"+client.getPort());
            executors.submit(new ExecuteClient(client));
        }
    }
}
