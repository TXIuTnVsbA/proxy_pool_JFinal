package com.demo.base;

import com.demo.model.Ip;
import com.demo.thread.MyThread;
import com.demo.util.HttpUtils;
import com.jfinal.kit.PropKit;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrawlingFrom66ip {
    private MyThread myThread=new MyThread(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
    public void saveProxyFromInternet(Integer count,Integer port,Integer threadCount,Integer timeout) throws Exception {
        Vector proxy=getProxyFromInternet(count,port);
        System.out.print(proxy.size()+"\n");
        proxy.forEach(p->{
            if(new Ip().dao().find("SELECT * FROM ip WHERE ip=?",p).isEmpty()){
                myThread.checkProxyForThread(String.valueOf(p),false,threadCount,timeout);
            }
            //myThread.checkProxyForThread(String.valueOf(p),false,threadCount,timeout);
        });
    }
    public void updateProxyFromDB(Integer threadCount,Integer timeout){
        Vector proxy=getProxyFromDB();
        proxy.forEach(p->{
            myThread.checkProxyForThread(String.valueOf(p),true,threadCount,timeout);
        });
    }
    private Vector getProxyFromInternet(Integer count,Integer port) throws Exception {
        Vector proxy=new Vector();
        String url = String.format("http://www.66ip.cn/mo.php?sxb=&tqsl=%s&port=%s",count,port);
        try {
            String html = HttpUtils.doGet(url);
            String lines[] = html.split("\r\n");
            for (int i = 0; i < lines.length; i++) {
                Pattern ipreg = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}:\\d{1,5}");
                Matcher proxyip = ipreg.matcher(lines[i]);
                while (proxyip.find()) {
                    //String proxy=proxyip.group().trim();
                    /*System.out.print(proxy+"\n");*/
                    try {
                        proxy.add(proxyip.group().trim());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return proxy;
    }
    private Vector getProxyFromDB(){
        Vector proxy=new Vector();
        try {
            new Ip().dao().find("SELECT * FROM ip").forEach(p->{
                proxy.add(p.getIp());
                //System.out.print(p.getIp()+"\n");
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return proxy;
    }
}
