package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {
    private String url;
    private ResourceBundle bundle;
    //用来储存cookies信息的变量
    private CookieStore cookieStore;

    @BeforeTest
    public void beforeTest(){
        //利用ResourceBundle从获得配置文件内容
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }
    @Test
    public void testGetCookies() throws IOException {
        //存放response返回结果
        String result;
        //从配置文件中拼接URL
        String uri= bundle.getString("getCookies.uri");
        String testUrl=this.url+uri;

        //测试逻辑代码编写
        HttpGet get = new HttpGet(testUrl);
        HttpClient client = new DefaultHttpClient();
        HttpResponse httpResponse = client.execute(get);
        result = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
        System.out.println(result);

        //获取cookies存储
        cookieStore = ((DefaultHttpClient) client).getCookieStore();
        //遍历cookies存储，取得每一个cookie
        List<Cookie> cookieList = cookieStore.getCookies();
        for (Cookie cookie: cookieList) {
            String name = cookie.getName();
            String values = cookie.getValue();
            System.out.println(name + ":" + values);

        }

    }
    //依赖testGetCookies是为了拿到cookies直接使用
   @Test(dependsOnMethods = "testGetCookies")
    public  void  testGetWithCookies() throws IOException {

       String uri = bundle.getString("test.get.with.cookies");
       String testUrl=this.url+uri;

       HttpGet get = new HttpGet(testUrl);
       DefaultHttpClient client = new DefaultHttpClient();

       //设置cookies信息，此时cookies信息是从上一个用例中获得的，当获取不到时，本用例也将失败
       client.setCookieStore(this.cookieStore);

       HttpResponse response = client.execute(get);
       
       //获取响应的状态码
       int statusCode = response.getStatusLine().getStatusCode();
       System.out.println("statusCode :"+statusCode);
       //判断响应状态码是否为200，如果是200则打印返回的数据
       if (statusCode == 200){
          String result = EntityUtils.toString(response.getEntity(),"utf-8");
           System.out.println(result);
       }


   }
}