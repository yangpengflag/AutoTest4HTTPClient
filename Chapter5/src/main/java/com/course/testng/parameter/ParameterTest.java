package com.course.testng.parameter;

import org.testng.annotations.Test;

public class ParameterTest {

    @Test(parameters = {"name","age"})
    public void test(String name,int age){
        System.out.println("name is :" + name+"; age is :" +age);
    }
}
