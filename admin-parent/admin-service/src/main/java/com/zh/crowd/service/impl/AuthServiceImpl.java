package com.zh.crowd.service.impl;

import com.zh.crowd.entity.Auth;
import com.zh.crowd.entity.AuthExample;
import com.zh.crowd.mapper.AuthMapper;
import com.zh.crowd.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {
    private AuthMapper baseMapper;

    @Autowired
    public void setBaseMapper(AuthMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 根据adminId获取已分配的权限值
     *
     * @param adminId adminId
     * @return 权限值列表
     */
    @Override
    public List<String> getAssignedAuthName(Integer adminId) {
        return baseMapper.selectAssignedAuthNameByAdminId(adminId);
    }

    /**
     * 获取所有的auth信息
     *
     * @return auth列表
     */
    @Override
    public List<Auth> getAllAuth() {
        return baseMapper.selectByExample(new AuthExample());
    }

    /**
     * 根据角色id获取所有的分配的权限id
     *
     * @param roleId 角色id
     * @return 已经分配的角色id列表
     */
    @Override
    public List<Integer> getAssignedAuthIdByRoleId(Integer roleId) {
        return baseMapper.selectAssignedAuthIdByRoleId(roleId);
    }

    /**
     * 给角色分配相应权限
     *
     * @param map 数据集
     */
    @Override
    public void assignRoleAuth(Map<String, List<Integer>> map) {
        // 1.获取roleId的值
        List<Integer> roleIdList = map.get("roleId");
        Integer roleId = roleIdList.get(0);
        // 2. 删除旧的关联数据
        baseMapper.deleteOldRelationship(roleId);
        // 3.获取 authIdList
        List<Integer> authIdList = map.get("authIdArray");
        // 4.判断 authIdList是否有效
        if (authIdList != null && authIdList.size() > 0) {
            baseMapper.insertNewRelationship(roleId, authIdList);
        }
    }
}
