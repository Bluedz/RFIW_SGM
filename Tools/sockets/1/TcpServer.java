package com.example.network;

import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer
{
    public static void main(String[] args) throws Exception
    {
        // �����������˵�socket����
        ServerSocket ss = new ServerSocket(5000);

        // ��������
        Socket socket = ss.accept();
        // ֱ�����ӽ�����֮�����Ż�����ִ��

        System.out.println("Connected Successfully!");

    }

}