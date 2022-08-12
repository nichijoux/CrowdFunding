package com.zh.crowd.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "统一返回结果")
public class Result {
    public static final Integer SUCCESS = 200;
    public static final Integer FAILURE = 201;

    @ApiModelProperty(value = "返回的代码")
    private Integer code;

    @ApiModelProperty(value = "返回的消息")
    private String message;

    @ApiModelProperty(value = "返回的数据结果")
    private Object data;

    public static Result success(Object data) {
        Result result = new Result();
        result.code = SUCCESS;
        result.data(data);
        return result;
    }

    public static Result success() {
        Result result = new Result();
        result.code = SUCCESS;
        return result;
    }

    public static Result failure() {
        Result result = new Result();
        result.code = FAILURE;
        return result;
    }

    public static Result failure(String message) {
        Result result = new Result();
        result.code = FAILURE;
        result.message(message);
        return result;
    }

    public Result message(String message) {
        this.message = message;
        return this;
    }

    public Result data(Object data) {
        this.data = data;
        return this;
    }

    public Result code(Integer code) {
        this.code = code;
        return this;
    }
}
