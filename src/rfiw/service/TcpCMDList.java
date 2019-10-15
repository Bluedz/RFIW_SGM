/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rfiw.service;


/**
 *
 * @author Zyh
 */
public class TcpCMDList {
//ACSys
    // in: card ID to sys
     // cmd: {"Use": "ACSys","OpCode": "Q", "DeviceID": "0001", "CardID": "0000666666", "Password": "00000000" }
     // return: {"Receive": "ok"} {"Use": "ACSys", "OpCode": "Q", "ReturnStatus": "1", "Password": "00000000"}     
    public static String doorBackOk = "{\"Use\": \"ACSys\", \"OpCode\": \"Q\", \"ReturnStatus\": \"1\", \"Password\": \"00000000\"}";
    
    // out: use this card can access
     // cmd: {"Use": "ACSys","OpCode": "Q","DeviceID": "0001","ReturnStatus": "1","Password": "00000000"} // ReturnStatus": "1"开闸，"0"不开
     // return: {"Use": "ACSys","OpCode": "Q","DeviceID": "0001","ReturnStatus": "1","Rceived": "ok"}
    
    public static String CMDAccessInOK (String devicedID, String flg, String psw){
        return "{\"Use\": \"ACSys\",\"OpCode\": \"Q\",\"DeviceID\": "
                + devicedID + ",\"ReturnStatus\": " + flg +",\"Password\": " + psw + "}";
    }
    //
    // out: Card with this ID can open the Door
     // cmd: {"Use": "ACSys","OpCode": "R","DeviceID": "0002","CardID": "0002926614","TimeOut": 15,"Password": "00000000"}
     // return: {"Use": "ACSys", "OpCode": "R", "DeviceID": "0002", "ReturnStatus": "1"} 
    // {"Use": "ACSys","OpCode": "R","DeviceID": "0002","ReturnStatus": "1","Password": "00000000"}
    public static String CMDOpenDoorByID (int timeOut, String cardID, String deviceID, String psw){
        return 
                "{\"Use\": \"ACSys\",\"OpCode\": \"R\",\"DeviceID\": \""+ deviceID + "\",\"CardID\": \""
                + cardID + "\",\"TimeOut\": " + timeOut + ",\"Password\": " + psw + "}";
    }
    
//RFID
    // out: 中控要求开始读取区域2（回收箱）
     // cmd: {"Use": "RFID", "OpCode": "Read", "Section": 2, "delay": 0}
     // return: {"Use": "RFID", "OpCode": "Read", "Section": "2", "ReturnStatus": "1"}
    
    // out: 中控要求返回区域1读取结果
     // cmd: {"Use": "RFID", "OpCode": "ReadCount", "Section": 1, "delay": 2}
     // return: {"Use": "RFID", "OpCode": "ReadCount", "Section": 1, "ReturnStatus": "1"}
    
    // out: 中控要求返回标签数据（回收箱）
      // cmd: {"Use": "RFID", "OpCode": "backData", "Section": 2, "delay": 0}
     // return: {"Use": "RFID", "OpCode": "backData", "Section": 2, "ReturnStatus": "1"}
    
    // out: 获得机号
     // cmd: {"Use":"RFID","OpCode":"getSequenceNumber"} 
     // return: {"Use":"RFID","OpCode":"getSequenceNumber", "ReturnStatus": "1"}    
    
    public static String CMDRFRead(String opCode, int sectionNum, int delay){
        return  "{\"Use\":\"RFID\",\"OpCode\":\""+ opCode + "\",\"Section\":"+ sectionNum + ",\"delay\":" + delay + "}";
    }
    public static String reCMDRFRead(String opCode, int sectionNum){
        return  "{\"Use\":\"RFID\",\"OpCode\":\""+ opCode + "\",\"Section\":"+ sectionNum + ",\"ReturnStatus\":1}";
    }
    public static String CMDRFGetMachineID(String opCode){
        return  "{\"Use\":\"RFID\",\"OpCode\":\"" + opCode + "\"}";
    }
    public static String reCMDRFGetMachineID(String opCode){
        return  "{\"Use\":\"RFID\",\"OpCode\":\""+ opCode + "\",\"ReturnStatus\":1}";
    }
    
    // in:  中控接收RF返回的标签数量
     // cmd: {"Use": "RFID", "OpCode": "ReadCount", "Section": 1, "count": 1}
     // return:  { "Receive": "ok"} 
    
    // in: 中控接收RF返回的标签值
     // cmd: {"Use": "RFID", "OpCode":"Read", "Section": 2, "data": "ABCDEF1234567890", "count": 1, "finish": 0} //finish为1表示结束
     // null, {"Use":"RFID","OpCode":"Read","Section":2,"data":"nofind","count":0,"finish":1}
     // return: { "Receive": "ok"}   
    
    // in: RF返回机号
     // cmd: {"Use":"RFID","OpCode":"getSequenceNumber","SequenceNumber":"E192124"}
     // return: { "Receive": "ok"} 
   
}
