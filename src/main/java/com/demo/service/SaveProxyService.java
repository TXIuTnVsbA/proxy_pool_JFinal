package com.demo.service;

import com.demo.base.CrawlingFrom66ip;

public class SaveProxyService {
    public static final SaveProxyService me =new SaveProxyService();
    public void start(){
        CrawlingFrom66ip c=new CrawlingFrom66ip();
        for(int i=0;i<10;i++){
            try {
                c.saveProxyFromInternet(100,0,64,500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
