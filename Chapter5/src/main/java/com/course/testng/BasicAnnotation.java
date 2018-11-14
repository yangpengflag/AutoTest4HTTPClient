package com.course.testng;

import org.testng.annotations.*;

public class BasicAnnotation {
    @BeforeClass
    public void beforeClass(){
        System.out.println("BeforeClass在类执行前执行");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("afterClass在类执行后执行");
    }


    @BeforeMethod
    public void beforeMethod(){
        System.out.println("beforeMethod在方法前运行");
    }
    @Test
    public void testCase1(){
        System.out.println("这是测试用例1");
    }
    @Test
    public void testCase2(){
        System.out.println("这是测试用例2");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("afterMethod在方法后运行");
    }

    @BeforeSuite
    public void beforSuite(){
        System.out.println("beforSuite测试套件");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("afterSuite测试套件");
    }
}
