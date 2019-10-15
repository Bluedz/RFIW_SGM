package rfiw.network;

import java.io.IOException;
import java.net.Socket;

public class MainClient
{

    /**
     *
     * @param port
     */
    public void mainClient(int port, String host) throws IOException {
        Socket socket = new Socket(host, port);
        new ClientInputThread(socket).start();
        new ClientOutputThread(socket).start();
        
    }
    
    public static void main(String[] args) throws Exception
    {
        Socket socket = new Socket("127.0.0.1", 6000);



    }
    
    
}