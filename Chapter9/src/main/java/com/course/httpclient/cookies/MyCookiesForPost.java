package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {

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
    @Test(dependsOnMethods = "testGetCookies")
    public void testPostMethod() throws IOException {
        String uri = bundle.getString("test.post.with.cookis");
        //拼接最终的测试地址
        String testUrl = this.url+uri;
        //声明一个client对象用来进行方法的执行
        DefaultHttpClient client = new DefaultHttpClient();
        //声明一个post方法
        HttpPost post = new HttpPost(testUrl);
        //添加参数
        JSONObject param = new JSONObject();
        param.put("name","huhansan");
        param.put("age","18");

        //设置请求头信息 设置Header
        post.setHeader("content-type","application/json");
        //把参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);

        //设置cookies信息
        client.setCookieStore(this.cookieStore);

        //声明一个对象来进行响应结果的存储
        String result;

        //执行post方法
        CloseableHttpResponse response = client.execute(post);

        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        //处理结果，判断结果是否达到预期
        //将返回的响应结果字符串转化为json对象
        JSONObject resultJson = new JSONObject(result);

        //获取结果的值
        String success = (String) resultJson.get("huhansan");
        String status = (String) resultJson.get("status");
        //具体的判断返回的结果值
        Assert.assertEquals("success",success);
        Assert.assertEquals("1",status);


    }
}
