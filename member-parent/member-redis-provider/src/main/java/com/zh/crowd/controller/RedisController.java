package com.zh.crowd.controller;

import com.zh.crowd.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redis")
public class RedisController {
    private StringRedisTemplate redisTemplate;

    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @ApiOperation(value = "远程调用,设置redis的key, value")
    @RequestMapping("remoteSetRedisKeyValue")
    public Result remoteSetRedisKeyValue(
            @RequestParam("key") String key,
            @RequestParam("value") String value) {
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "远程调用,设置redis的key, value并带有超时时间")
    @RequestMapping("remoteSetRedisKeyValueWithTimeout")
    public Result remoteSetRedisKeyValueWithTimeout(
            @RequestParam("key") String key,
            @RequestParam("value") String value,
            @RequestParam("time") long time,
            @RequestParam("timeUnit") TimeUnit timeUnit) {
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(key, value, time, timeUnit);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "远程调用,根据key获取value值")
    @RequestMapping("remoteGetRedisValueByKey")
    public Result remoteGetRedisValueByKey(
            @RequestParam("key") String key) {
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            String value = operations.get(key);
            return Result.success(value);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "远程调用,删除redis的key")
    @RequestMapping("remoteDeleteRedisKey")
    public Result remoteDeleteRedisKey(
            @RequestParam("key") String key) {
        try {
            redisTemplate.delete(key);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(e.getMessage());
        }
    }
}
