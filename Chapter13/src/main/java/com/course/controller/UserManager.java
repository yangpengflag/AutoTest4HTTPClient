package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;


@RestController
@Api(value = "v1",description = "用户管理系统")
@RequestMapping(value = "v1")
public class UserManager {
    @Autowired
    private SqlSessionTemplate template;

    @ApiOperation(value = "登录接口",httpMethod = "POST")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Boolean login(HttpServletResponse response, @RequestBody User user){
        int i  = template.selectOne("login", user);
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        System.out.println("查到的结果是"+i);
        if(i == 1){
            System.out.println(user.getUserName());
            return true;
        }
        return false;
    }

    public Boolean verifyCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            System.out.println("cookies为空");
            return false;
        }
        for (Cookie cookie: cookies) {
            if(cookie.getName().equals("login")&&cookie.getValue().equals("true")){
                System.out.println("验证通过");
                return true;
            }

        }

        return false;
    }

    @ApiOperation(value = "添加用户接口",httpMethod = "POST")
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public Boolean addUser(HttpServletRequest request,@RequestBody User user){
        Boolean x = verifyCookies(request);
        int result = 0;
        if (x){
            result = template.insert("addUser", user);
        }
        if (result>0){
            System.out.println("添加用户的数量是："+result);
            return true;
        }

        return false;

    }

    @ApiOperation(value = "获取用户信息接口",httpMethod = "POST")
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    public List<User> getUserInfo(HttpServletRequest request,@RequestBody User user){
        Boolean x = verifyCookies(request);
        if (x){
            List<User> users = template.selectList("getUserInfo", user);
            System.out.println("获取的用户数量是："+users.size());
            return users;
        }
     return null;
    }

    @ApiOperation(value = "更新/删除用户信息接口",httpMethod = "POST")
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    public int updateUser(HttpServletRequest request,@RequestBody User user){
        Boolean x = verifyCookies(request);
        int result = 0 ;
        if (x){
             result = template.update("updateUserInfo", user);
        }
        System.out.println("更新用户的数量是:"+result);
        return result;

    }


}
