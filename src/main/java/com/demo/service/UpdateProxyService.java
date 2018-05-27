package com.demo.service;

import com.demo.base.CrawlingFrom66ip;

public class UpdateProxyService {
    public static final UpdateProxyService me =new UpdateProxyService();
    public void start(){
        new CrawlingFrom66ip().updateProxyFromDB(64,200);

    }
}
