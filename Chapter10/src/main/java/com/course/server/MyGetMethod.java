package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是我的全部get方法")
public class MyGetMethod {

    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    @ApiOperation(value = "通过get方法获取cookies",httpMethod = "GET")
    public String getCookies(HttpServletResponse response){
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "获取Cookies信息成功";
    }

    /***
     * 要求客户端携带cookies访问
     * 这是一个需要写到cookies才能访问的请求
     */
    @RequestMapping(value = "/get/with/cookies",method = RequestMethod.GET)
    @ApiOperation(value = "要求客户端携带cookies访问",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request) throws UnsupportedEncodingException {
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            return "必须带cookies信息";
        }
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("login")&&cookie.getValue().equals("true")){
                return "Access success成功";
            }
        }

        return "必须带cookies信息";
    }

    /***
     * 开发一个需要携带参数才能访问的get请求
     * 第一种实现方式 url：key=value&key=value
     * 模拟获取商品列表
     */
    @RequestMapping(value = "/get/with/param",method = RequestMethod.GET)
    @ApiOperation(value = "需要携带参数才能访问的get请求",httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start,@RequestParam Integer end){

        Map<String,Integer> myMap = new HashMap<>();
        myMap.put("鞋子",198);
        myMap.put("衣服",998);
        myMap.put("袜子",19);


        return myMap;
    }

    /**
     * 第二种需要携带参数访问的get请求
     * url:ip:port/get/with/param/10/1000
     */
    @RequestMapping(value = "/get/with/param/{start}/{end}")
    @ApiOperation(value = "RESTFUL风格携带参数访问的get请求",httpMethod = "GET")
    public Map<String,Integer> mygetList(@PathVariable Integer start,@PathVariable Integer end){
        Map<String,Integer> myMap = new HashMap<>();
        myMap.put("鞋子",198);
        myMap.put("衣服",998);
        myMap.put("袜子",19);


        return myMap;
    }
}
