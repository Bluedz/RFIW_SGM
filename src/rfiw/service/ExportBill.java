/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rfiw.service;
import java.util.*;
import java.text.*;
import java.io.*;

/**
 *
 * @author Zyh
 */
public class ExportBill {

	public void buildTxt(String m,String c) {
		//m机号 c卡号
		//格式化文件名
                String[][] billList = rfiw.data.BillData.billList;
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddHHmmss");
		String txtName = ft.format(date);
		//文件路径
		txtName = "D:\\ExportData\\RFIW-"+m+"-"+txtName +".txt";
		System.out.println(txtName);
		try {
            BufferedWriter out = new BufferedWriter(new FileWriter(txtName));
			out.write("0001 "+c+"\r\n");
			int e = 1;
			for(int i=0;i<billList.length - (billList.length- rfiw.data.BillData.tagsEndBit);i++){
				//每行序号从0002开始
				e = e + 1;
				String t = "0" + e;
				while (t.length() < 4) {
					t = "0" + t;
				}
				out.write(t+" ");
				for(int j=1;j<4;j++){
					out.write(billList[i][j]+" ");
				}
				out.write("\r\n");
			}
            out.close();
            System.out.println("文件创建成功！");
        } catch (IOException e) {
        }
	};
 
	public static void main(String []args) {
//            String machineId = "E192124";
//            String cardId = "0000666666";
//    
//            String[][] billList = new String [][] {
//            {"code", "mat", "batch", "stock"},
//            {"b00", "b01", "b02","b03"},
//            {"b10", "b11", "b12","b13"},
//            {"b20", "b21", "b22","b23"}
//            };
//		BillData x= new BillData();
//		x.buildTxt(x.machineId,x.cardId);
	}
	
    
}
