/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rfiw.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import rfiw.data.*;


/**
 *
 * @author Zyh
 */
public class IOProcess {

        public static void resolvingCMD (String strCMD) throws Exception {
        String strOpCode ;
        String strUse  ;
        //RF
        String RFMachineID;
        int RFCount; 
        String RFTagData;
        int codeFinish;
        int RFTagsCount;
        
        //ACsys
        String CardID;
                
        JSONObject jsonObject  = decodeCMD(strCMD);
        strOpCode = jsonObject.getString("OpCode");
        strUse = jsonObject.getString("Use");
        
        if("RFID".equals(strUse)){
            if("ReadCount".equals(strOpCode)){
                RFCount = jsonObject.getInteger("count");
                 fillRFCount(RFCount);
            }else if("Read".equals(strOpCode)){
                RFTagData = jsonObject.getString("data");
                codeFinish = jsonObject.getInteger("finish");
                
                RFTagsCount = jsonObject.getInteger("count");
                if(RFTagsCount != 0){
                    try{
                        fillRFTagList(RFTagData);
                    }
                    catch(Exception e){
                    System.out.println("标签数据数据写入订单列表失败");
                    System.out.println(e);
                  }                    
                }
                //
                if(codeFinish==1){
                    // 结束处理
                    rfiw.data.ControlData.tagsEndFlag = true;
                    //
                    System.out.println("tagsEndBit:" + rfiw.data.BillData.tagsEndBit);
                    System.out.println("billList[0][0]" + rfiw.data.BillData.billList[0][0]);
                    System.out.println("billList[1][0]" + rfiw.data.BillData.billList[1][0]);
                }
            }else if("getSequenceNumber".equals(strOpCode)){
                RFMachineID = jsonObject.getString("SequenceNumber");
                rfiw.data.ControlData.machineID = RFMachineID;
                // new rfiw.service.ProcessProps().write(ControlData.configURI, ControlData.configName, "machineID", RFMachineID);
                System.out.println("which one?:" + ControlData.machineID);
            }
        }else if("ACSys".equals(strUse)){
            if("Q".equals(strOpCode)){
                CardID = jsonObject.getString("CardID");
                if(!rfiw.data.ControlData.inBillFlow){  
                    fillCardID(CardID);                   
                             
                  // 请求RF开始读回收箱                 
                   rfiw.RFIW.goRequest("Read");
                   // System.out.println("do ask 1");
                }else {
                    System.out.println("inBillFlow? = " + rfiw.data.ControlData.inBillFlow);
                    new rfiw.network.TcpClient().tcpClient(rfiw.data.ControlData.ACSysIP01, ControlData.ACSysLsnPort, "Q");
                }
                new rfiw.network.TcpClient().tcpClient(rfiw.data.ControlData.ACSysIP01, ControlData.ACSysLsnPort, "Q");
              
            }

        }else System.out.println("unkown command");
    }
    
    
    public static void fillRFCount(int count){
        //
        rfiw.data.ControlData.amoutOfRFIDInExit = count;
        System.out.println("RFCount = " + rfiw.data.ControlData.amoutOfRFIDInExit);
    }
        
    public static void fillRFTagList(String hexStr){
        //
        String str = rfiw.service.StrHexExchange.hex2Str(hexStr);
        String[] strArr = str.split("@");
        System.out.println(str +"="+strArr[0]);
        rfiw.data.BillData.appendLine(strArr);
        
    }
    
    public static void fillCardID(String str){
        //
        rfiw.data.BillData.billOwnerID = str;
        System.out.println("billOwnerID = " + rfiw.data.BillData.billOwnerID);
    }    
        
    public static JSONObject decodeCMD (String strCMD){
        String jsonStr = strCMD;
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        return jsonObject;        
    }
    
    public static void test(){
        System.out.println("=");
    }
    public static void main(String arg[]){

    
    }
}
