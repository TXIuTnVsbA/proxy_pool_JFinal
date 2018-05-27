package com.demo;

import com.demo.controller.IndexController;
import com.demo.task.SaveProxyTask;
import com.demo.task.UpdateProxyTask;
import com.jfinal.config.*;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.cron4j.Cron4jPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.demo.model._ip_pool_MappingKit;

public class Config extends JFinalConfig{
    @Override
    public void configConstant(Constants constants) {
        PropKit.use("config.txt");
        constants.setDevMode(PropKit.getBoolean("devMode"));
    }

    @Override
    public void configRoute(Routes routes) {
        routes.add("/", IndexController.class);
    }

    @Override
    public void configEngine(Engine engine) {

    }
    public static DruidPlugin createDruidPlugin() {
        return new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
    }
    @Override
    public void configPlugin(Plugins plugins) {
        DruidPlugin dp = createDruidPlugin();
        plugins.add(dp);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        _ip_pool_MappingKit.mapping(arp);
        plugins.add(arp);
        Cron4jPlugin cp = new Cron4jPlugin();
        cp.addTask("*/30 * * * *",new UpdateProxyTask());
        cp.addTask("0 */1 * * *",new SaveProxyTask());
        plugins.add(cp);
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {

    }
}
