package com.course.model;

import lombok.Data;

@Data
public class GetUserListCase {
    private int id;
    private String age;
    private String sex;
    private String userName;
    private String expected;
}
