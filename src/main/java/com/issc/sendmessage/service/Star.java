package com.issc.sendmessage.service;

import com.issc.sendmessage.Utils.TimingUtil;

public class Star {
    public static void main(String[] args) {
        //TimingUtil.timer();
        try {
            SendMail.creakMail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
