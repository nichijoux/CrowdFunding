package com.zh.crowd.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Admin {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "创建时间")
    private String createTime;
}