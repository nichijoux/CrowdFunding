package com.zh.crowd.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Order {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "订单号")
    private String orderNum;

    private String payOrderNum;

    private Double orderAmount;

    private Integer invoice;

    private String invoiceTitle;

    private String orderRemark;

    private String addressId;
}