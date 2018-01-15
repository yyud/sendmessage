package com.issc.sendmessage.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * properties文件读取类
 *
 */
public class PropertiesReaderUtil {
    public  static String loadProperties(String str) {
        Properties properties = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("ss.properties");//加载Java项目根路径下的配置文件
            // properties.load(input);// 加载属性文件
            properties.load(new InputStreamReader(input, "utf-8"));//加载属性文件
            str = properties.getProperty(str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return str;
    }

    /**
     * 读取文件
     * @param str
     * @param filename
     * @return
     */
    public  static String loadProperties(String str,String filename) {
        Properties properties = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(filename);//加载Java项目根路径下的配置文件
            properties.load(input);// 加载属性文件
            str = properties.getProperty(str);
        } catch (IOException io) {
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return str;
    }
}