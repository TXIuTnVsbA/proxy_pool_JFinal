package com.demo.service;

import com.demo.base.CrawlingFrom66ip;

public class UpdateProxyService {
    public static final UpdateProxyService me =new UpdateProxyService();
    public void start(){
        CrawlingFrom66ip c=new CrawlingFrom66ip();
        c.updateProxyFromDB(64,500);

    }
}
