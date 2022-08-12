package com.zh.crowd.client;

import com.zh.crowd.entity.Member;
import com.zh.crowd.entity.vo.DetailProjectVO;
import com.zh.crowd.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "crowd-mysql")
public interface MySqlClient {

    @ApiOperation(value = "远程调用,根据账号获取成员")
    @RequestMapping(value = "member/remoteGetMemberByAccount")
    Member remoteGetMemberByAccount(@RequestParam("account") String account);

    @ApiOperation(value = "远程调用,添加member")
    @RequestMapping("member/remoteAddMember")
    Result remoteAddMember(@RequestBody Member member);

    @ApiOperation(value = "获取首页数据")
    @RequestMapping("project/remoteGetIndexProjectData")
    Result remoteGetIndexProjectData();

    @ApiOperation(value = "远程调用,添加众筹项目")
    @RequestMapping("project/remoteAddProject")
    Result remoteAddProject();

    @ApiOperation(value = "根据项目id获取项目详情")
    @RequestMapping(value = "project/getProjectDetails/{projectId}")
    DetailProjectVO remoteGetProjectDetails(@PathVariable("projectId") Integer projectId);
}
