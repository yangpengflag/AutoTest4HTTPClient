package com.course.testng.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class SuiteConfig {
    @BeforeSuite
    public void beforSuite(){
        System.out.println("befor Suite执行了");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("after Suite执行了");
    }
    @BeforeTest
    public void beforTest(){
        System.out.println("brefor test");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("after test");
    }
}
