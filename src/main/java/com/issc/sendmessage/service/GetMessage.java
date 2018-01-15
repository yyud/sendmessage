package com.issc.sendmessage.service;

import com.issc.sendmessage.Utils.PropertiesReaderUtil;
import com.alibaba.fastjson.JSON;
import com.issc.sendmessage.bean.DataObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * 获取资源信息
 * @author
 */
public class GetMessage {
    /**
     * 获取资源信息
     * @return
     * @throws IOException
     */
    public static DataObject resultMessage() throws IOException {
        // 设置
        int x = (int)(Math.random()*20000+1);
        //设置api接口
        //String url = "http://api.avatardata.cn/Joke/QueryJokeByTime?key=616ccbb7801c49f1a806e6d9a7056b5c&page="+x+"&rows=1&sort=desc&time=1518745237";
        String url = PropertiesReaderUtil.loadProperties("url")+x+PropertiesReaderUtil.loadProperties("url2")+PropertiesReaderUtil.loadProperties("key");
        String str = GetMessage.run(url); //获取数据Json对象
        DataObject dataObject = GetMessage.parsingData(str); //解析Json对象
        // System.out.println(str);
        //System.out.println(dataObject.getResult().get(0).getContent());
        //str = dataObject.getResult().get(0).getContent();
        return dataObject;
    }

    /**
     * 利用OKHTTP获取资源
     * @param url 资源接口
     * @return
     * @throws IOException
     */
    public static String run(String url) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient(); //创建Client对象
        Request request = new Request.Builder().url(url).build(); //资源请求
        Response response = okHttpClient.newCall(request).execute(); //请求响应
        if (response.isSuccessful()) {//判断是否成功 成功就返回 不成功就抛出异常
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }


    /**
     * 解析字符串并封装到对象
     * @param str 资源字符串
     * @return
     */
    public static DataObject parsingData(String str){
        DataObject dataObject= JSON.parseObject(str,DataObject.class);//解析字符串

        //JSONObject result=JSON.parseObject(str);
       // str = JSON.toJSONString(dataObject); //讲对象转化为字符串
        return dataObject;//返回对象结果
    }

}
