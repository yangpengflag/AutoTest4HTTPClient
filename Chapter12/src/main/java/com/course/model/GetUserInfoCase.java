package com.course.model;

import lombok.Data;

@Data
public class GetUserInfoCase {
    private int id;
    private int UserId;
    private String expected;
}
