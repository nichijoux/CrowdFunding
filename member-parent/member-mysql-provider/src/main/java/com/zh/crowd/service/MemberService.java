package com.zh.crowd.service;

import com.zh.crowd.entity.Member;

public interface MemberService {
    // 根据账号获取成员
    Member getMemberByAccount(String account);

    // 添加成员
    void addMember(Member member);
}
