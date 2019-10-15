/*-
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rfiw.data;

/**
 *
 * @author Zyh
 */
public class BillData {
    public static String billOwnerID = "0011609586"; //0000666666 not default
    public static int tagsEndBit = 0;
    public static String[][] billList = new String[100][4];

    private static String[][] billLsit0 =
            new String [][] {
            {"code", "mat", "batch", "stock"}
        };

    // 执行写入时检测是否物料已被计入结算列表，有效的新增数据则更新列表
    public static boolean appendLine(String[] str){
       boolean flg = true;
       int length, length2;

       for(int i=0; i < tagsEndBit ; i++){

          if ((billList[i][0]).equals(str[0])){ 
              if((billList[i][1]).equals(str[1])){
                  if((billList[i][2]).equals(str[2])){
                      if((billList[i][3]).equals(str[3])){
                          flg = false;
                      }
                  }
              }
  
          }   
       }
        
       if (flg){
        length = billList[tagsEndBit].length;
        length2 = str.length;
        System.arraycopy(str, 0, billList[tagsEndBit], 0, length);
        tagsEndBit++;
       }else{
       // 提示重复扫描或已存在与列表内
        System.out.println("本条标签数据重复扫描或已存在与列表内");
       }

       return flg;
        
    } 
    
    public static void main(String args[]){

    }
    
}
