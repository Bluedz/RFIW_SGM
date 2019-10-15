
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rfiw;
import java.net.URL;
import javax.swing.JFrame;
import rfiw.data.ControlData;
import rfiw.network.MainServer;

// import rfiw.data.BillData;
/**
 *
 * @author Zyh
 */
public class RFIW {

    /**
     */       
    public RFIW (){
        // init();
    }
    public void init(){
        // frameTag = "Welcome";// frameTag = "Bill";// frameTag = "Bye";
        // switchJframe();
    }
    
    /**
    * Router
     * @param jframe
    */
    public static void switchJframe(JFrame jframe){        
        jframe.dispose();
        switch (rfiw.data.ControlData.frameTag) {
            case "Welcome":
                new Pg_Welcome().setVisible(true);
                // welcomePage.setVisible(true);
                break;
            case "Bill":
                JFrame billPage = new Pg_Bill();
                       billPage.setVisible(true);
                break;
            case "Bye":
                new Pg_Bye().setVisible(true);
                break;
            default:
                new Pg_Welcome().setVisible(true);
                break;
        }
    }
    
    public static void getRFMachineID(){
         goRequest("getSequenceNumber");
    }
    
    public static void goRequest(String opCode){
        String RFHost = rfiw.data.ControlData.RFReadIP01;
        int RFPort = rfiw.data.ControlData.RFLsnPort;
        // RF        
        try { new rfiw.network.TcpClient().tcpClient(RFHost, RFPort, opCode); 
        }catch(Exception ex){System.out.println(ex);}
        
        //ACSys
    }
    public static void resetFlow(){
        rfiw.data.ControlData.inBillFlow = false;
        rfiw.data.ControlData.amoutOfRFIDInExit = 9999;
        rfiw.data.BillData.billOwnerID = "";
        rfiw.data.BillData.tagsEndBit = 0;
        rfiw.data.ControlData.tagsEndFlag = false;
        String[] fillLine = new String[]{null, null, null, null}; 
        for (int i=0; i < rfiw.data.BillData.billList.length; i++){
            rfiw.data.BillData.billList[i] = fillLine;
        }
    }
    
    public static void main(String[] args) throws Exception {
 

        // System.out.println((new ControlData().getClass().getResource("")).getPath());
        ControlData.printParams();
        
        //
        goRequest("getSequenceNumber");
        
        //        
        JFrame welcomePage = new Pg_Welcome();
        welcomePage.setVisible(true);
        // MainServer Server = 
        new rfiw.network.MainServer();        
        

    }
    
    
    
}
