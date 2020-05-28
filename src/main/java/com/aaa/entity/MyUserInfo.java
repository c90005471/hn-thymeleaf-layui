package com.aaa.entity;

import lombok.Data;

@Data
public class MyUserInfo {
    private Integer userid;

    private String username;

    private String password;
    private String salt;

}