package com.zh.crowd.service.impl;

import com.zh.crowd.entity.Member;
import com.zh.crowd.entity.MemberExample;
import com.zh.crowd.mapper.MemberMapper;
import com.zh.crowd.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService {
    private MemberMapper baseMapper;

    @Autowired
    public void setBaseMapper(MemberMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 根据账号获取成员
     *
     * @param account 账号
     * @return 账号对应的成员
     */
    @Transactional(readOnly = true)
    @Override
    public Member getMemberByAccount(String account) {
        MemberExample example = new MemberExample();
        MemberExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(account);
        return baseMapper.selectByExample(example).get(0);
    }

    /**
     * 添加成员
     *
     * @param member 成员实体
     */
    @Override
    public void addMember(Member member) {
        baseMapper.insert(member);
    }
}
