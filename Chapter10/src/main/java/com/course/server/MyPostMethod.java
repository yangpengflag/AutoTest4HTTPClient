package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/",description = "全部POST请求")
@RequestMapping("/v1")
public class MyPostMethod {
    //用来存储ccokies信息
    private static Cookie cookies;

   @RequestMapping(value = "/login",method = RequestMethod.POST)
   @ApiOperation(value="登录接口，登录成功获取cookies信息",httpMethod = "POST")
    public String login(HttpServletResponse response, @RequestParam(value = "userName",required=true) String userName,
                        @RequestParam(value = "passWord",required=true) String passWord){
       if(userName.equals("zhangsan")&&passWord.equals("123456")){
           Cookie cookie = new Cookie("login","true");
           response.addCookie(cookie);
           return "登录成功";
       }

        return "用户名或密码错误";
    }
   @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
   @ApiOperation(value = "获取用户列表",httpMethod = "POST")
    public String getUserList(HttpServletRequest request, @RequestBody User user){
       User u;
       //获取cookies信息，并验证是否合法
       Cookie[] cookies = request.getCookies();
       for (Cookie cookie:cookies) {
           if(cookie.getName().equals("login")&&cookie.getValue().equals("true")
           &&user.getUserName().equals("zhangsan")&&user.getPassWord().equals("123456")){

               u = new User("lisi","666666","ss","fale",28);

               return u.toString();


           }

       }

       return "参数不合法";
   }
}
