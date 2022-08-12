package com.zh.crowd.service;

import com.github.pagehelper.PageInfo;
import com.zh.crowd.entity.Role;

import java.util.List;

public interface RoleService {
    // 关键词分页查询角色信息
    PageInfo<Role> pageQueryRole(Integer pageNum, Integer pageSize, String keyword);

    // 添加角色信息
    void addRole(Role role);

    // 更新角色信息
    void updateRole(Role role);

    // 根据角色id列表删除角色信息
    void deleteRole(List<Integer> roleIdList);

    // 根据adminId获取已分配的角色
    List<Role> getAssignedRole(Integer adminId);

    // 根据adminId获取未分配角色
    List<Role> getUnAssignedRole(Integer adminId);
}
