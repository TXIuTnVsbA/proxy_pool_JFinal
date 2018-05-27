package com.demo.controller;

import com.demo.model.Ip;
import com.jfinal.core.Controller;

public class IndexController extends Controller{
    private String iplist="";
    public void index() {
        renderText("server running");
    }
    public void get(){
        getall();
    }
    public void getall(){
        Ip ip = new Ip();
        try {
            int arg0=Integer.parseInt(getPara(0));
            if(arg0 != 0 && arg0 <= 100){

                ip.find("SELECT * FROM `ip` WHERE id >= ((SELECT MAX(id) FROM `ip`)-(SELECT MIN(id) FROM `ip`)) * RAND() + (SELECT MIN(id) FROM `ip`) LIMIT ?",arg0).forEach(p->{
                    iplist=iplist+p.getIp()+"\n";
                });
                renderText(iplist);
            }else {
                renderText("Fuck You!");
            }
        }catch (Exception e){
            renderText(e.toString());
        }finally {
            ip.clear();
        }
    }
    public void gethttp(){
        Ip ip = new Ip();
        try {
            int arg0=Integer.parseInt(getPara(0));
            if(arg0 != 0 && arg0 <= 100){

                ip.find("SELECT * FROM `ip` WHERE id >= ((SELECT MAX(id) FROM `ip`)-(SELECT MIN(id) FROM `ip`)) * RAND() + (SELECT MIN(id) FROM `ip`) AND (STATUS = 1 OR STATUS =3) LIMIT ?",arg0).forEach(p->{
                    iplist=iplist+p.getIp()+"\n";
                });
                renderText(iplist);
            }else {
                renderText("Fuck You!");
            }
        }catch (Exception e){
            renderText(e.toString());
        }finally {
            ip.clear();
        }
    }
    public void gethttps(){
        Ip ip = new Ip();
        try {
            int arg0=Integer.parseInt(getPara(0));
            if(arg0 != 0 && arg0 <= 100){

                ip.find("SELECT * FROM `ip` WHERE id >= ((SELECT MAX(id) FROM `ip`)-(SELECT MIN(id) FROM `ip`)) * RAND() + (SELECT MIN(id) FROM `ip`) AND (STATUS = 2 OR STATUS =3) LIMIT ?",arg0).forEach(p->{
                    iplist=iplist+p.getIp()+"\n";
                });
                renderText(iplist);
            }else {
                renderText("Fuck You!");
            }
        }catch (Exception e){
            renderText(e.toString());
        }finally {
            ip.clear();
        }
    }
}
