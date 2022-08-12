package com.zh.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zh.crowd.constant.CrowdConstant;
import com.zh.crowd.entity.Admin;
import com.zh.crowd.entity.AdminExample;
import com.zh.crowd.exception.AccountAlreadyExistException;
import com.zh.crowd.mapper.AdminMapper;
import com.zh.crowd.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private AdminMapper baseMapper;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setBaseMapper(AdminMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 分页查询admin用户
     *
     * @param pageNum  当前页
     * @param pageSize 每页记录数
     * @param keyword  关键词
     * @return admin分页列表
     */
    @Override
    public PageInfo<Admin> pageQueryAdmin(Integer pageNum, Integer pageSize, String keyword) {
        // 1.调用PageHelper的静态方法开启分页功能
        PageHelper.startPage(pageNum, pageSize);
        // 2.执行查询
        List<Admin> list = baseMapper.selectAdminByKeyword(keyword);
        // 3.封装到PageInfo对象
        return new PageInfo<>(list);
    }

    /**
     * 保存admin用户
     *
     * @param admin 要保存的admin用户
     */
    @Override
    public void saveAdmin(Admin admin) {
        String password = admin.getPassword();
        admin.setPassword(passwordEncoder.encode(password));
        // 创建时间
        admin.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        try {
            baseMapper.insert(admin);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof DuplicateKeyException) {
                throw new AccountAlreadyExistException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
        }
    }

    /**
     * 删除admin用户
     *
     * @param adminId admin用户Id
     */
    @Override
    public void deleteAdmin(Integer adminId) {
        baseMapper.deleteByPrimaryKey(adminId);
    }

    /**
     * 更新admin用户
     *
     * @param admin admin用户更新信息
     */
    @Override
    public void updateAdmin(Admin admin) {
        baseMapper.updateByPrimaryKeySelective(admin);
    }

    /**
     * 根据账号获取admin用户
     *
     * @param account 账号
     * @return 账号对应的admin实体
     */
    @Override
    public Admin getAdminByAccount(String account) {
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(account);
        return baseMapper.selectByExample(example).get(0);
    }

    /**
     * 根据adminId获取admin实体
     *
     * @param adminId adminId
     * @return admin实体
     */
    @Override
    public Admin getAdmin(Integer adminId) {
        return baseMapper.selectByPrimaryKey(adminId);
    }

    /**
     * 给adminId分配相关角色信息
     *
     * @param adminId    用户id
     * @param roleIdList 角色id列表
     */
    @Override
    public void assignRole(Integer adminId, List<Integer> roleIdList) {
        // 删除旧的用户-角色关系
        baseMapper.deleteOldRelationship(adminId);
        // 插入新的用户-角色关系
        if (roleIdList != null && !roleIdList.isEmpty())
            baseMapper.insertNewRelationship(adminId, roleIdList);
    }
}
