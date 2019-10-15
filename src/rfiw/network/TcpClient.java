/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rfiw.network;
import rfiw.data.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
/**
 *
 * @author WWW
 */
public class TcpClient {
    public void tcpClient(String host, int port, String opCode)throws Exception{
        Socket socket = new Socket(host, port);
        //* String backStr = "{\"Use\": \"ACSys\", \"OpCode\": \"R\", \"DeviceID\": \"0002\", \"ReturnStatus\": \"1\"}";
        String backStr = null;
        String strCMD = null ;
        if(opCode.equals("R")){
            strCMD = rfiw.service.TcpCMDList.CMDOpenDoorByID (15, rfiw.data.BillData.billOwnerID, rfiw.data.ControlData.ACSysDevice02, ControlData.ACSysPSW);
             backStr = "{\"Use\": \"ACSys\", \"OpCode\": \"R\", \"DeviceID\": \"0002\", \"ReturnStatus\": \"1\"}";
        }else if(opCode.equals("Q")) {
            if(rfiw.data.ControlData.inBillFlow){
            strCMD = rfiw.service.TcpCMDList.CMDAccessInOK(rfiw.data.ControlData.ACSysDevice01, "0", ControlData.ACSysPSW);
            // "{\"Use\": \"ACSys\",\"OpCode\": \"Q\",\"DeviceID\": \"0001\",\"ReturnStatus\": \"0\",\"Password\": \"00000000\"}";
            backStr = "{\"Use\": \"ACSys\",\"OpCode\": \"Q\",\"DeviceID\": \"0001\",\"ReturnStatus\": \"0\",\"Received\": \"ok\"}";
            }else {
            strCMD = rfiw.service.TcpCMDList.CMDAccessInOK(rfiw.data.ControlData.ACSysDevice01, "1", ControlData.ACSysPSW) ;
            // "{\"Use\": \"ACSys\",\"OpCode\": \"Q\",\"DeviceID\": \"0001\",\"ReturnStatus\": \"1\",\"Password\": \"00000000\"}";
            backStr = "{\"Use\": \"ACSys\",\"OpCode\": \"Q\",\"DeviceID\": \"0001\",\"ReturnStatus\": \"1\",\"Received\": \"ok\"}";
            rfiw.data.ControlData.inBillFlow = true;
            }
        }else if(opCode.equals("Read")){
            strCMD = rfiw.service.TcpCMDList.CMDRFRead(opCode, 2, 0);
            backStr = rfiw.service.TcpCMDList.reCMDRFRead(opCode, 2);
        }else if(opCode.equals("ReadCount")){
            strCMD = rfiw.service.TcpCMDList.CMDRFRead(opCode, 1, 2);
            backStr = rfiw.service.TcpCMDList.reCMDRFRead(opCode, 1);
        }else if(opCode.equals("backData")){
            strCMD = rfiw.service.TcpCMDList.CMDRFRead(opCode, 2, 0);
            backStr = rfiw.service.TcpCMDList.reCMDRFRead(opCode, 2);;
        }else if(opCode.equals("getSequenceNumber")){
            strCMD = rfiw.service.TcpCMDList.CMDRFGetMachineID(opCode);
            backStr = rfiw.service.TcpCMDList.reCMDRFGetMachineID(opCode);
        }   
        
        // "{\"Use\": \"ACSys\",\"OpCode\": \"R\",\"DeviceID\": \"0002\",\"CardID\": \"0002926614\",\"TimeOut\": 15,\"Password\": \"00000000\"}";
        // CMDOpenDoorByID (int timeOut, String cardID, String deviceID);
        
        // 
        OutputStream os = socket.getOutputStream();        
        os.write(strCMD.getBytes());
        System.out.println("SendOut:" + strCMD);
        InputStream is = socket.getInputStream();
        byte[] buffer = new byte[1024];
        int length = is.read(buffer);
        String str = new String(buffer, 0, length);
        System.out.println("ReturnV:" + str);
 
        if ( backStr.equals(str)){
            System.out.println("Retrun from Device is right");

        }else{
            System.out.println("Retrun from Device is wrong");
            System.out.println("- Wbakstr:" + backStr);
            System.out.println("- Rbakstr:" + str);
            os.write(strCMD.getBytes());
        }
        
        is.close();
        os.close();
        socket.close();
        
    }
    
/*    private String getBackStr(Socket socket)throws Exception{
        InputStream is = socket.getInputStream();
        byte[] buffer = new byte[1024];
        int length = is.read(buffer);
        String str = new String(buffer, 0, length);
        System.out.println(str);
        //is.close();
        return str;
    }*/
    
    public static void main(String[] args) throws Exception
    {
        // new TcpClient().tcpClient("127.0.0.1", 6000);
    }
}
