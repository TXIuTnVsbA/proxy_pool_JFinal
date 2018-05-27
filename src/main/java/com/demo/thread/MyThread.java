package com.demo.thread;

import com.demo.model.Ip;
import com.demo.util.HttpUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

public class MyThread {
    private Integer i=0;
    private Vector threads=new Vector();
    private String url;
    private String name= "com.mysql.jdbc.Driver";
    private String user;
    private String passwd;
    public MyThread(String dburl,String user,String passwd){
        this.url=dburl;
        this.user=user;
        this.passwd=passwd;
    }
    public void sql(String sql){
        Connection conn = null;
        try {
            Class.forName(name);
            conn = DriverManager.getConnection(url, user, passwd);//获取连接
            conn.setAutoCommit(false);//关闭自动提交，不然conn.commit()运行到这句会报错
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            conn.setAutoCommit(false);
            PreparedStatement pst = (PreparedStatement)conn.prepareStatement("");//准备执行语句
            // 添加执行SQL
            pst.addBatch(sql);
            try {
                // 执行操作
                pst.executeBatch();
                // 提交事务
                conn.commit();
            }catch (Exception e){
                e.printStackTrace();
            }
            // 清空上一次添加的数据
            // 头等连接
            pst.close();
            conn.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void checkProxyForLogic(String proxy,Boolean flag){
        Integer status=0;
        Boolean http = false;
        Boolean https = false;
        http = HttpUtils.checkProxy("http://2018.ip138.com/ic.asp",proxy);
        https= HttpUtils.checkProxy("https://myip.kkcha.com/",proxy);
        if (http) {
            status = 1;
            System.out.print(proxy + "\thttp\tOK\n");
        }
        if (https) {
            status = 2;
            System.out.print(proxy + "\thttps\tOK\n");
        }
        if (http && https) {
            status = 3;
            System.out.print("\n");
        }
        if (!http && !https) {
            status = 4;
            System.out.print(proxy + "\tNot\tOK\n");
        }
        if(flag){//update
            if(status == 4){
                deleProxy(proxy);
            }else {
                updateProxy(proxy,status);
            }
        }else {//save
            saveProxy(proxy,status);
        }
    }
    public void checkProxyForThread(String proxy,Boolean flag,Integer threadCount,Integer timeout){
        i+=1;
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    checkProxyForLogic(proxy, flag);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        threads.add(thread);
        if((i % threadCount) == 0){
            threads.forEach(p->{
                Thread thread1 =(Thread) p;
                try {
                    thread1.join(timeout);
                    if(thread1.isAlive()){
                        thread1.interrupt();
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }
    }
    public Boolean saveProxy(String proxy,Integer status){
        try {
            sql(String.format("INSERT INTO `ip` (ip,STATUS) VALUES (\"%s\",%s);",proxy,status));
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public Boolean updateProxy(String proxy,Integer status){
        try {
            sql(String.format("UPDATE `ip` SET STATUS=\"%s\" WHERE ip=\"%s\";",status,proxy));
            //System.out.print(String.format("UPDATE `ip` SET STATUS=\"%s\" WHERE ip=\"%s\";",status,proxy)+"\n");
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public Boolean deleProxy(String proxy){
        try {
            sql(String.format("DELETE FROM `ip` WHERE ip=\"%s\";",proxy));
            //System.out.print(String.format("DELETE FROM `ip` WHERE ip=\"%s\";",proxy)+"\n");
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
