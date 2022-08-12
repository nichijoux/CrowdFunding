package com.zh.crowd.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Menu {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "父节点id")
    private Integer pid;

    @ApiModelProperty(value = "菜单名")
    private String name;

    @ApiModelProperty(value = "节点附带url地址，点击菜单项时要跳转的地址")
    private String url;

    @ApiModelProperty(value = "节点图标样式")
    private String icon;

    @ApiModelProperty(value = "存储子节点的集合，初始化以避免空指针异常")
    private List<Menu> children = new ArrayList<>();

    @ApiModelProperty(value = "控制节点是否默认展开，设置为true表示默认打开")
    private Boolean open = true;
}