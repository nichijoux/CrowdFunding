package com.zh.crowd.entity;

import lombok.Data;

@Data
public class Member {
    private Integer id;

    private String account;

    private String username;

    private String password;

    private String email;

    private Integer authstatus;

    private Integer usertype;

    private String realname;

    private String cardnum;

    private Integer accountType;
}