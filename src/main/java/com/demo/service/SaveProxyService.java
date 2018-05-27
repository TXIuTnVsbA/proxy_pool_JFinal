package com.demo.service;

import com.demo.base.CrawlingFrom66ip;

public class SaveProxyService {
    public static final SaveProxyService me =new SaveProxyService();
    public void start(){
        for(int i=0;i<10;i++){
            try {
                new CrawlingFrom66ip().saveProxyFromInternet(100,0,64,500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
