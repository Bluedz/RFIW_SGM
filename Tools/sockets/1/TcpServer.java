package com.example.network;

import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer
{
    public static void main(String[] args) throws Exception
    {
        // 创建服务器端的socket对象
        ServerSocket ss = new ServerSocket(5000);

        // 监听连接
        Socket socket = ss.accept();
        // 直到连接建立好之后代码才会往下执行

        System.out.println("Connected Successfully!");

    }

}