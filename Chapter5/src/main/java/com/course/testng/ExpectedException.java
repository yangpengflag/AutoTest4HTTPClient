package com.course.testng;

import org.testng.annotations.Test;

public class ExpectedException {
    /***
     * 什么时候用到异常用例？
     * 在我们期望结果为一个异常的时候，比如传入一个不合法的参数，程序应该抛出异常，
     * 此时不抛出异常则为错误，与实际期望值不符
     */

    //这是一个会失败的异常测试
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionFailed(){

        System.out.println("这是一个失败的异常测试");
    }

    //这是一个成功的异常测试
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionSuccess(){
        System.out.println("这是一个成功的异常测试");
        throw new RuntimeException();

    }
}
