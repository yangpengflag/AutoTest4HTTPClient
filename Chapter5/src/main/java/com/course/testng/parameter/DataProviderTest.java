package com.course.testng.parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataProviderTest {

    @Test(dataProvider = "dataTest")
    public void dataProvideTest(String name, int age) {
        System.out.println("name is :" + name + "; age is :" + age);
    }

    @DataProvider(name = "dataTest")
    public Object[][] privoderData() {
        Object[][] o = new Object[][]{
                {"zhangsan", 10},
                {"lisi", 20},
                {"wangwu", 50}
        };

        return o;
    }

    @Test(dataProvider = "methodData")
    public void test1(String name, int age) {
        System.out.println("test11111 name is :" + name + "; age is :" + age);
    }

    @Test(dataProvider = "methodData")
    public void test2(String name, int age) {
        System.out.println("test2222 name is :" + name + "; age is :" + age);
    }

    @DataProvider(name = "methodData")
    public Object[][] methodDataTest(Method method) {
        Object[][] result = null;
        if (method.getName().equals("test1")) {
            result = new Object[][]{
                    {"zhangsan", 30},
                    {"lisi", 40},
                    {"wangwu", 50}
            };
        } else if (method.getName().equals("test2")) {
            result = new Object[][]{
                    {"zhaoliu", 60},
                    {"fengqi", 70}
            };

        }

        return result;
    }
}
