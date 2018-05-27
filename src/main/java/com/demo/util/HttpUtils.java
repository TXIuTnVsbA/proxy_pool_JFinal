package com.demo.util;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.builder.HCB;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpUtils {
    public static String doGet(String url) throws Exception{
        try {
            Header[] header = HttpHeader.custom()
                    .accept("*/*")
                    .acceptEncoding("gzip, deflate")
                    .acceptLanguage("zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                    .userAgent("Mozilla/5.0 (Linux; U; Android 4.0.4; en-gb; GT-I9300 Build/IMM76D) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30")
                    .build();
            HttpClient client = HCB.custom().ssl().retry(3).build();
            HttpConfig config = HttpConfig.custom()
                    .client(client)
                    .headers(header)
                    .url(url)
                    .encoding("utf-8");
            String html = HttpClientUtil.get(config);
            return html;
        }catch (Exception e){
            return "null";
        }
    }
    public static Boolean checkProxy(String url,String proxy){
        try {
            String host=proxy.split(":")[0];
            Integer port=Integer.parseInt(proxy.split(":")[1]);
            HttpClient client = null;
            try {
                client = HCB.custom().proxy(host,port).timeout(2000).retry(5).ssl().build();
            } catch (Exception e) {
                client = HCB.custom().proxy(host,port).timeout(2000).retry(5).build();
            }
            HttpConfig config = HttpConfig.custom().encoding("utf-8").client(client).url(url);
            try {
                String result =HttpClientUtil.get(config);
                //System.out.print(result);
                Matcher m = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}").matcher(result);
                if(m.find()){
                    //System.out.print(m.group()+"\n");
                    return true;
                }
            }catch (Exception e){
                //e.printStackTrace();
            }
        }catch (Exception e){
            //e.printStackTrace();
        }
        return false;
    }
}
