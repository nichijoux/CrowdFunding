package com.zh.crowd.service;

import com.github.pagehelper.PageInfo;
import com.zh.crowd.entity.Admin;

import java.util.List;

public interface AdminService {
    // 分页查询admin用户
    PageInfo<Admin> pageQueryAdmin(Integer pageNum, Integer pageSize, String keyword);

    // 添加admin用户
    void saveAdmin(Admin admin);

    // 删除admin用户
    void deleteAdmin(Integer adminId);

    // 更新admin用户
    void updateAdmin(Admin admin);

    // 根据账号获取admin用户
    Admin getAdminByAccount(String account);

    // 根据adminId获取admin实体
    Admin getAdmin(Integer adminId);

    // 给adminId分配相关角色信息
    void assignRole(Integer adminId, List<Integer> roleIdList);
}
