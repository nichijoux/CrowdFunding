package com.zh.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zh.crowd.entity.Role;
import com.zh.crowd.entity.RoleExample;
import com.zh.crowd.mapper.RoleMapper;
import com.zh.crowd.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleMapper baseMapper;

    @Autowired
    public void setBaseMapper(RoleMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 关键词分页查询角色信息
     *
     * @param pageNum  当前页
     * @param pageSize 每页记录数
     * @param keyword  关键词
     * @return 分页角色列表
     */
    @Override
    public PageInfo<Role> pageQueryRole(Integer pageNum, Integer pageSize, String keyword) {
        // 开启分页
        PageHelper.startPage(pageNum, pageSize);
        // sql查询
        List<Role> roleList = baseMapper.selectRoleByKeyword(keyword);
        // 封装为pageInfo对象
        return new PageInfo<>(roleList);
    }

    /**
     * 添加角色信息
     *
     * @param role 要添加的角色实体
     */
    @Override
    public void addRole(Role role) {
        baseMapper.insert(role);
    }

    /**
     * 更新角色信息
     *
     * @param role 要更新的角色实体
     */
    @Override
    public void updateRole(Role role) {
        baseMapper.updateByPrimaryKey(role);
    }

    /**
     * 根据角色id列表删除角色信息
     *
     * @param roleIdList 角色id列表
     */
    @Override
    public void deleteRole(List<Integer> roleIdList) {
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(roleIdList);
        baseMapper.deleteByExample(example);
    }

    /**
     * 根据adminId获取已分配角色
     *
     * @param adminId adminId
     * @return 已分配角色列表
     */
    @Override
    public List<Role> getAssignedRole(Integer adminId) {
        return baseMapper.selectAssignedRole(adminId);
    }

    /**
     * 根据adminId获取未分配角色
     *
     * @param adminId adminId
     * @return 未分配角色列表
     */
    @Override
    public List<Role> getUnAssignedRole(Integer adminId) {
        return baseMapper.selectUnAssignedRole(adminId);
    }
}
