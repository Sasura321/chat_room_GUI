package com.github.java.vo;

import lombok.Data;

/**
 * Auther： zsm
 * Date： 2019/8/13 10:27
 */
@Data
public class MessageVo {

    /**
     * 表示告知服务器要进行的动作，1：表示用户注册，2：表示私聊
     */
    private String type;

    /**
     * 发送到服务器的具体内容
     */
    private String content;

    /**
     *私聊告知服务器要将信息发给哪个用户
     */
    private String to;
}
