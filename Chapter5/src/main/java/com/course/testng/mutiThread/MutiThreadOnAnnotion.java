package com.course.testng.mutiThread;

import org.testng.annotations.Test;

public class MutiThreadOnAnnotion {

    @Test(invocationCount = 10,threadPoolSize = 10)
    public void test(){
        System.out.println(1);
        System.out.printf("Thread id is ：%s%n",Thread.currentThread().getId());
    }
}
