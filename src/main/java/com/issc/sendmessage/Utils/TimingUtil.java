package com.issc.sendmessage.Utils;

import com.issc.sendmessage.service.SendMail;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 计时器
 * @author yyud
 *
 */
public class TimingUtil {

    public static void timer(){
         Calendar calendar = Calendar.getInstance();
         calendar.set(Calendar.HOUR_OF_DAY, 9); // 控制时
         calendar.set(Calendar.MINUTE, 30);    // 控制分
         calendar.set(Calendar.SECOND, 0);    // 控制秒
         Date time = calendar.getTime();     // 得出执行任务的时间,
         Timer timer = new Timer();
         timer.scheduleAtFixedRate(new TimerTask() {
             public void run() {
                 try {
                     SendMail.creakMail();
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             }
          }, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行
    }

}
