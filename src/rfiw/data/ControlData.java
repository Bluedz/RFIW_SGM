/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rfiw.data;

import java.net.URI;
import java.net.URL;

/**
 *
 * @author Zyh
 */
public class ControlData {
    // config file
    public static String configURI = "";//(new ControlData().getClass().getResource("")).getPath(); //"";
    public static String configName = "project.properties";
    public static String RFIWVersion = setParams("RFIWVersion");
    
    //ACSys 
    public static String ACSysIP01 = setParams("ACSysIP01"); //"192.168.90.203" "127.0.0.1"; 
    public static String ACSysIP02 = setParams("ACSysIP02"); //"192.168.90.204";  
    public static int ACSysLsnPort = Integer.parseInt(setParams("ACSysLsnPort"));//6000; //6004;
    public static int ACSysCntPort = Integer.parseInt(setParams("ACSysCntPort"));//6000;
    public static String ACSysDevice01 = setParams("ACSysDevice01"); //"0001"; 
    public static String ACSysDevice02 = setParams("ACSysDevice02"); //"0002";
    public static String ACSysPSW = setParams("ACSysPSW"); //"00000000";
    
    // RFSys
    public static String machineID = setParams("machineID"); //"E192124";
    public static String RFReadIP01 = setParams("RFReadIP01"); // = "192.168.11.74"  "127.0.0.1";
    public static int RFLsnPort = Integer.parseInt(setParams("RFLsnPort"));//6010;
    public static int RFCntPort = Integer.parseInt(setParams("RFCntPort"));//6000;    

    //
    public static Boolean inBillFlow = false; //false;    
    public static String frameTag; // frametag  界面切换用    
    public static int amoutOfRFIDInExit = 9999; // amount of RF-label in exit Area    
    public static boolean tagsEndFlag = false;
    
    public static String setParams(String key){
        return new rfiw.service.ProcessProps().readOneKey(configURI, configName, key);
    }
    
    public static void printParams(){
        System.out.println(configURI);
        System.out.println("ACSysIP01 " + ACSysIP01);
        System.out.println("ACSysIP02 " + ACSysIP02);
        System.out.println("ACSysLsnPort " + ACSysLsnPort);
        System.out.println("ACSysCntPort " + ACSysCntPort);
        System.out.println("ACSysDevice01 " + ACSysDevice01);
        System.out.println("ACSysDevice02 " + ACSysDevice02);
        System.out.println("ACSysPSW " + ACSysPSW);
        System.out.println("machineID " + machineID);
        System.out.println("RFReadIP01 " + RFReadIP01);
        System.out.println("RFLsnPort " + RFLsnPort);
        System.out.println("RFCntPort " + RFCntPort);
        System.out.println("RFIWVersion " + RFIWVersion);
    }
    
    public static void main(String arg[]){
    
    }
}
