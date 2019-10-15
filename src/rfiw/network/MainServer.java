package rfiw.network;

import java.net.ServerSocket;
import java.net.Socket;

public class MainServer
{
    // public static void main(String[] args) throws Exception
    public MainServer() throws Exception
    {
         
        int port2 = 6000;
       // ServerSocket serverSocket1 = new ServerSocket(port1);
        ServerSocket serverSocket2 = new ServerSocket(port2);

        while (true)
        {
         //   Socket socket1 = serverSocket1.accept();
            Socket socket2 = serverSocket2.accept();

          //  new ServerInputThread(socket1).start();
            new ServerInputThread2(socket2).start();

        }

    }

}