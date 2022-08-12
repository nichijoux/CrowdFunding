package com.zh.crowd.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Project {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "众筹项目名")
    private String projectName;

    @ApiModelProperty(value = "项目描述")
    private String projectDescription;

    @ApiModelProperty(value = "众筹需要的钱数")
    private Long money;

    private Integer day;

    private Integer status;

    private String deploydate;

    private Long supportmoney;

    private Integer supporter;

    private Integer completion;

    private Integer memberid;

    private String createdate;

    private Integer follower;

    private String headerPicturePath;
}