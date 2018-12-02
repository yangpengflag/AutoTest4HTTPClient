package com.course.utils;

import com.course.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;


public class ConfigFile {
    private static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);

    public static String getUrl(InterfaceName name){
        //application配置的接口测试主机地址
        String host = bundle.getString("test.url");
        //接口项目地址
        String uri="";

        //根据enum中的接口名，给对应接口的uri赋值，用于拼接接口全链接
        if(name.equals(InterfaceName.GETUSERINFO)){
            uri=bundle.getString("getUserInfo.uri");
        }else if (name.equals(InterfaceName.ADDUSER)){
            uri=bundle.getString("addUser.uri");
        }else if (name.equals(InterfaceName.GETUSERLIST)){
            uri=bundle.getString("getUserList.uri");
        }else if(name.equals(InterfaceName.UPDATEUSERINFO)){
            uri=bundle.getString("updateUserInfo.uri");
        }else if(name.equals(InterfaceName.LOGIN)){
            uri=bundle.getString("login.uri");
        }

        //拼接后的测试地址
        String testUrl = host + uri;
        return testUrl;
    }
}
