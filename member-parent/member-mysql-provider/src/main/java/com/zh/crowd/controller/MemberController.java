package com.zh.crowd.controller;

import com.zh.crowd.constant.CrowdConstant;
import com.zh.crowd.entity.Member;
import com.zh.crowd.result.Result;
import com.zh.crowd.service.MemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @ApiOperation(value = "远程调用,根据账号获取用户")
    @RequestMapping(value = "remoteGetMemberByAccount")
    Member remoteGetMemberByAccount(
            @RequestParam("account") String account) {
        try {
            return memberService.getMemberByAccount(account);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "远程调用,添加member")
    @RequestMapping("remoteAddMember")
    Result remoteAddMember(
            @RequestBody Member member) {
        try {
            memberService.addMember(member);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof DuplicateKeyException) {
                return Result.failure(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
            return Result.failure(e.getMessage());
        }
    }
}
