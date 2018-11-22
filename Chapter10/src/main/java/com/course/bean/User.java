package com.course.bean;

import lombok.Data;

@Data
public class User {
    private String userName;
    private String passWord;
    private String name;
    private String sex;
    private int age;

    public User() {
    }

    public User(String userName, String passWord, String name, String sex, int age) {
        this.userName = userName;
        this.passWord = passWord;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
}
