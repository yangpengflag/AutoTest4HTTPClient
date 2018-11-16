package com.course.testng.mutiThread;

import org.testng.annotations.Test;

public class MutiThreadOnXml {
    @Test
    public void test1(){
        System.out.println(1);
        System.out.printf("test1 Thread id is ：%s%n",Thread.currentThread().getId());
    }
    @Test
    public void test2(){
        System.out.println(1);
        System.out.printf("test2 Thread id is ：%s%n",Thread.currentThread().getId());
    }

    @Test
    public void test3(){
        System.out.println(1);
        System.out.printf("test3 Thread id is ：%s%n",Thread.currentThread().getId());
    }
}
