package com.course.testng.parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {

    @Test(dataProvider = "dataTest")
    public void dataProvideTest(String name,int age){
        System.out.println("name is :" + name+"; age is :" +age);
    }

    @DataProvider(name = "dataTest")
    public Object[][] privoderData(){
        Object[][] o = new Object[][]{
                {"zhangsan",10},
                {"lisi",20},
                {"wangwu",50}
        };

        return o;
    }
}
