package rfiw.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import java.io.OutputStream;

public class ServerInputThread extends Thread
{
    private Socket socket;

    public ServerInputThread(Socket socket)
    {
        super();
        this.socket = socket;
    }

    @Override
    public void run()
    {
        try
        {

            InputStream is = socket.getInputStream();

            OutputStream os = socket.getOutputStream();
            
            String repOk2RF = "{\"Receive\": \"ok\"}";
            

            while (true)
            {
                byte[] buffer = new byte[1024];
                int length = is.read(buffer);
                String str = new String(buffer, 0, length);
                System.out.println(str);             

                os.write(repOk2RF.getBytes());

            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}