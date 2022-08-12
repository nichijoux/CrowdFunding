package com.zh.crowd.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Auth {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "权限名")
    private String name;

    @ApiModelProperty(value = "菜单名")
    private String title;

    @ApiModelProperty(value = "父菜单id")
    private Integer categoryId;
}