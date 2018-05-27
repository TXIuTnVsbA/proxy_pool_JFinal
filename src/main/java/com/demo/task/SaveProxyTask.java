package com.demo.task;

import com.demo.service.SaveProxyService;
import com.jfinal.plugin.cron4j.ITask;

public class SaveProxyTask implements ITask {
    @Override
    public void stop() {
        SaveProxyService.me.start();
    }

    @Override
    public void run() {

    }
}
