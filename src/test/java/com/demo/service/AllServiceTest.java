package com.demo.service;
import com.demo.Config;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.kit.PropKit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AllServiceTest {
    private static final Plugins plugins=new Plugins();
    @Before
    public void setUpBeforeClass() {
        //System.out.print("setUpBeforeClass\n");
        PropKit.use("config.txt");
        createJFinalConfig().configPlugin(plugins);
        plugins.getPluginList().forEach(p->p.start());
    }
    protected JFinalConfig createJFinalConfig() {
        return new Config();
    }
    @Test
    public void test() throws Exception {
        AllService.me.start();
    }
    @After
    public void tearDown() {
        //System.out.print("tearDown\n");
        plugins.getPluginList().forEach(p->p.stop());
    }
}
