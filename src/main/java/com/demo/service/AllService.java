package com.demo.service;

import com.demo.base.CrawlingFrom66ip;

public class AllService {
    public static final AllService me = new AllService();
    public void start(){
        CrawlingFrom66ip c = new CrawlingFrom66ip();
        c.updateProxyFromDB(64,500);
        for(int i=0;i<10;i++){
            try {
                c.saveProxyFromInternet(100,0,64,500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
