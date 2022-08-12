package com.zh.crowd.mapper;

import com.zh.crowd.entity.Role;
import com.zh.crowd.entity.RoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    // 根据关键词获取角色
    List<Role> selectRoleByKeyword(String keyword);

    // 根据用户id获取已分配角色
    List<Role> selectAssignedRole(Integer adminId);

    // 根据用户id获取未分配角色
    List<Role> selectUnAssignedRole(Integer adminId);
}