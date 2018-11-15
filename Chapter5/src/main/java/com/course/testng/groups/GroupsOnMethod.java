package com.course.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupsOnMethod {
    @Test(groups = "server")
    public void test1(){
        System.out.println("这是服务端组的测试用例1111111");
    }

    @Test(groups = "server")
    public void test2(){
        System.out.println("这是服务端组的测试用例222222");
    }

    @Test(groups = "client")
    public void test3(){
        System.out.println("这是客户端组的测试用例3333");
    }

    @Test(groups = "client")
    public void test4(){
        System.out.println("这是客户端组的测试用例44444");
    }
    @BeforeGroups("server")
    public void beforeGoupsOnServer(){
        System.out.println("这是server端执行前执行的");
    }
    @AfterGroups("server")
    public void afterGoupsOnServer(){
        System.out.println("这是server端组运行之后运行的");
    }

    @BeforeGroups("client")
    public void beforeGoupsOnClient(){
        System.out.println("这是client端执行前执行的");
    }
    @AfterGroups("client")
    public void afterGoupsOnClient(){
        System.out.println("这是client端组运行之后运行的");
    }
}
