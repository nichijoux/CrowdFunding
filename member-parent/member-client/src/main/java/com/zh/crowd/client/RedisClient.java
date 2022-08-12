package com.zh.crowd.client;

import com.zh.crowd.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.TimeUnit;

@FeignClient(name = "crowd-redis")
public interface RedisClient {
    @ApiOperation(value = "远程调用,设置redis的key, value")
    @RequestMapping("redis/remoteSetRedisKeyValue")
    Result remoteSetRedisKeyValue(@RequestParam("key") String key,
                                  @RequestParam("value") String value);

    @ApiOperation(value = "远程调用,设置redis的key, value并带有超时时间")
    @RequestMapping("redis/remoteSetRedisKeyValueWithTimeout")
    Result remoteSetRedisKeyValueWithTimeout(@RequestParam("key") String key,
                                             @RequestParam("value") String value,
                                             @RequestParam("time") long time,
                                             @RequestParam("timeUnit") TimeUnit timeUnit);

    @ApiOperation(value = "远程调用,根据key获取value值")
    @RequestMapping("redis/remoteGetRedisValueByKey")
    Result remoteGetRedisValueByKey(@RequestParam("key") String key);

    @ApiOperation(value = "远程调用,删除redis的key")
    @RequestMapping("redis/remoteDeleteRedisKey")
    Result remoteDeleteRedisKey(@RequestParam("key") String key);
}
