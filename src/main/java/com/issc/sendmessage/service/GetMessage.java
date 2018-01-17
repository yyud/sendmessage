package com.issc.sendmessage.service;

import com.alibaba.fastjson.JSON;
import com.issc.sendmessage.bean.DataObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * 获取资源信息
 * @author yyud
 */
public class GetMessage {
    /**
     * 获取资源信息
     * @return 返回json数据对象
     * @throws IOException 文件异常
     */
    public static DataObject resultMessage() throws IOException {
        // 设置
        int x = (int)(Math.random()*20000+1);
        //设置api接口
        //String url = "http://api.avatardata.cn/Joke/QueryJokeByTime?key=616ccbb7801c49f1a806e6d9a7056b5c&page="+x+"&rows=1&sort=desc&time=1518745237";
        //String url = PropertiesHelper.readePropertiesFile("url")+x+PropertiesHelper.readePropertiesFile("url2")+PropertiesHelper.readePropertiesFile("key");
        SendMail sendMail = SendMail.getSendMailObject(); //获取SendMail对象
        //获取api接口
        String url = sendMail.getValue().get(8)+x+sendMail.getValue().get(12)+sendMail.getValue().get(11);
        //获取数据Json对象
        String str = GetMessage.run(url);
        //解析Json对象并返回数据对象
        return GetMessage.parsingData(str);
    }

    /**
     * 利用OKHTTP获取资源
     * @param url 资源接口
     * @return 返回
     * @throws IOException 文件异常
     */
    public static String run(String url) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient(); //创建Client对象
        Request request = new Request.Builder().url(url).build(); //资源请求
        Response response = okHttpClient.newCall(request).execute(); //请求响应
        if (response.isSuccessful()) {//判断是否成功 成功就返回 成功就抛出异常
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }


    /**
     * 解析字符串并封装到对象
     * @param str 资源字符串
     * @return 返回数据对象
     */
    public static DataObject parsingData(String str){
        return JSON.parseObject(str,DataObject.class);//返回对象结果
    }

}
