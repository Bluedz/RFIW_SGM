package com.example.network;

import java.net.Socket;

public class TcpClient
{
    public static void main(String[] args) throws Exception
    {
        Socket socket = new Socket("127.0.0.1", 5000);
    }

}