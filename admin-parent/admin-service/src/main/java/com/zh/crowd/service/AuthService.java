package com.zh.crowd.service;

import com.zh.crowd.entity.Auth;

import java.util.List;
import java.util.Map;

public interface AuthService {
    // 根据adminId获取已分配的权限值
    List<String> getAssignedAuthName(Integer adminId);

    // 获取所有的auth信息
    List<Auth> getAllAuth();

    // 根据角色id获取所有的分配的权限id
    List<Integer> getAssignedAuthIdByRoleId(Integer roleId);

    // 给角色分配相应权限
    void assignRoleAuth(Map<String, List<Integer>> map);
}
