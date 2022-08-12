package com.zh.crowd.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "角色名")
    private String name;
}