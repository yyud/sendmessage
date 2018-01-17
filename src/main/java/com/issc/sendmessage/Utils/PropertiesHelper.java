package com.issc.sendmessage.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * properties文件读取类
 *
 */
public class PropertiesHelper {
    /**
     * 读取默认文件（ss.properties）指定键值
     * @param key 键
     * @return 读出的字符串
     */
    public  static String readePropertiesFile(String key) {
        return readePropertiesFile(key,"ss.properties");
    }

    /**
     *  读取指定列表指定键值
     * @param key 键
     * @param filename  文件名
     * @return 读出字符串
     */
    public  static String readePropertiesFile(String key, String filename) {
        Properties properties = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(filename);//加载Java项目根路径下的配置文件
            properties.load(new InputStreamReader(input, "utf-8"));//加载属性文件
            key = properties.getProperty(key);//获取值
        } catch (IOException e) {
            e.printStackTrace();
        } finally {//关闭输入流
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return key;
    }

    /**
     * 读取指定文件 键值列表 到String List
     * @param filename 文件名
     * @param key 键
     */
    public  static void readePropertiesFile(String filename, List<String> key, List<String> value) {
        Properties properties = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(filename);//加载Java项目根路径下的配置文件
            properties.load(new InputStreamReader(input, "utf-8"));//加载属性文件
            for (String keys : properties.stringPropertyNames()) {
                key.add(keys);//获取键
                value.add(properties.getProperty(keys)); //获取值
            }
        } catch (IOException ignored) {
            ignored.printStackTrace();
        } finally {          //关闭输入流
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取文件并解密
     * @param filename 文件名
     * @param secretkey 解密密钥
     * @param key 键
     * @param value  值
     */
    public static void decryptionReadePropertiesFile(String filename, String secretkey, List<String> key, List<String> value) {
        List<String> qed = new ArrayList<>();
        List<String> quaalude  = new ArrayList<>();
        readePropertiesFile(filename,qed,quaalude);//
        for (int i = 0;i<qed.size();i++){
            key.add(AESEncryptionUtil.decryption(secretkey,qed.get(i)));
            value.add(AESEncryptionUtil.decryption(secretkey,quaalude.get(i)));
        }
    }

    /**
     * 向指定文件写入数据
     * @param filename 文件名
     * @param key 键
     * @param value 值
     * @param r 是否追加
     */
    private static void writePropertiesFile(String filename,List<String> key, List<String> value,Boolean r) {
        Properties prop = new Properties();
        for (int i = 0;i<key.size();i++){
            prop.setProperty(key.get(i), value.get(i));
        }
        FileOutputStream oFile = null;
        try {
            //保存属性到b.properties文件
            oFile = new FileOutputStream(filename, r);//r = true表示追加打开,false每次都是清空再重写
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

    /**
     * 解密读取文件放入指定文件
     * @param filename1 读
     * @param filename2 写
     * @param secretkey 密钥
     */
    public static void encryptionWritePropertiesFile(String filename1, String filename2, String secretkey) {
        List<String> key = new ArrayList<>();
        List<String> value  = new ArrayList<>();
        List<String> qekey = new ArrayList<>();
        List<String> qevalue  = new ArrayList<>();
        PropertiesHelper.readePropertiesFile(filename1,key,value);
        for (int i = 0;i<key.size();i++){
            qekey.add(AESEncryptionUtil.decryption(secretkey,key.get(i)));
            qevalue.add(AESEncryptionUtil.decryption(secretkey,value.get(i)));
        }
        writePropertiesFile(filename2,qekey,qevalue,true);
    }

}