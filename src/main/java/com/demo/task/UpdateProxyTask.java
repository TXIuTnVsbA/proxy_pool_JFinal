package com.demo.task;

import com.demo.service.UpdateProxyService;
import com.jfinal.plugin.cron4j.ITask;

public class UpdateProxyTask implements ITask {
    @Override
    public void stop() {

    }

    @Override
    public void run() {
        UpdateProxyService.me.start();
    }
}
