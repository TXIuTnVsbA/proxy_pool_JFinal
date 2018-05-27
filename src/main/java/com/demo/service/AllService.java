package com.demo.service;

import com.demo.base.CrawlingFrom66ip;

public class AllService {
    public static final AllService me = new AllService();
    public void start(){
        new CrawlingFrom66ip().updateProxyFromDB(64,200);
        for(int i=0;i<10;i++){
            try {
                new CrawlingFrom66ip().saveProxyFromInternet(100,0,64,200);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
