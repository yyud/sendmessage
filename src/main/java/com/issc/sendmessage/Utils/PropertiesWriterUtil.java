package com.issc.sendmessage.Utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Properties;

/**
 * 写入
 */
public class PropertiesWriterUtil {

    /**
     *
     * @param key 键
     * @param value 值
     */
    private static void writePropertiesFile(String[] key,String[] value) {
        Properties prop = new Properties();
        for (int i = 0;i<key.length;i++){
            prop.setProperty(key[i], value[i]);
        }
        FileOutputStream oFile = null;
        try {
            //保存属性到b.properties文件
            oFile = new FileOutputStream("configfile.properties", true);//true表示追加打开,false每次都是清空再重写
            prop.store(new OutputStreamWriter(oFile, "utf-8"), "111");//这个就是生成的properties文件中第一行的注释文字乱码
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (oFile != null) {
                try {
                    oFile.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        //PropertiesWriterUtil.writePropertiesFile();
    }

}
