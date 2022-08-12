package com.zh.crowd.controller;

import com.zh.crowd.client.MySqlClient;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    private MySqlClient mySqlClient;

    @Autowired
    public void setMySqlClient(MySqlClient mySqlClient) {
        this.mySqlClient = mySqlClient;
    }

    @ApiOperation(value = "访问首页")
    @RequestMapping("/")
    public String index(){

        return "index";
    }
}
