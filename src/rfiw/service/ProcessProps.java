/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rfiw.service;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream; 
import java.util.Iterator;
import java.util.Properties; 
/**
 *
 * @author Zyh
 */
public class ProcessProps {
    public String readOneKey(String uri, String fileName, String key) {
            String keyValue = null;
            Properties prop = new Properties(); 
            try{
                //InputStream in=new BufferedInputStream (ClassLoader.getSystemResourceAsStream(uri));
                InputStream in = new BufferedInputStream (new FileInputStream(uri+fileName));
                prop.load(in);     
                keyValue = prop.getProperty(key);            
                in.close();               
            }
            catch(Exception e){
                     System.out.println(e);
            }            
            return keyValue;
    }
    
    public void readAllKeys(String uri, String fileName) {
            
            Properties prop = new Properties(); 
            try{
                InputStream in = new BufferedInputStream (new FileInputStream(uri+fileName));
                prop.load(in);     
                Iterator<String> it=prop.stringPropertyNames().iterator();
                while(it.hasNext()){
                    String key=it.next();
                    System.out.println(key+":"+prop.getProperty(key));
                    
                }
                in.close();

            }
            catch(Exception e){
                     System.out.println(e);
            }
    }
    
    public void write(String uri, String fileName, String key, String keyValue){
        Properties prop = new Properties(); 
        try{
             FileOutputStream oFile = new FileOutputStream(uri+fileName, true);//true表示追加打开
             prop.setProperty(key, keyValue);
             oFile.close();
        }
        catch(Exception e){
              System.out.println(e);
        }        
    
    }
    
}
