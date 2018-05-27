package com.demo.task;

import com.demo.service.SaveProxyService;
import com.jfinal.plugin.cron4j.ITask;

public class SaveProxyTask implements ITask {
    @Override
    public void stop() {

    }

    @Override
    public void run() {
        SaveProxyService.me.start();
    }
}
