package com.zh.crowd.mapper;

import com.zh.crowd.entity.Auth;
import com.zh.crowd.entity.AuthExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthMapper {
    long countByExample(AuthExample example);

    int deleteByExample(AuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Auth record);

    int insertSelective(Auth record);

    List<Auth> selectByExample(AuthExample example);

    Auth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByExample(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

    // 根据角色id获取已分配的权限id
    List<Integer> selectAssignedAuthIdByRoleId(Integer roleId);

    // 删除旧的角色-权限关系
    void deleteOldRelationship(Integer roleId);

    // 插入新的角色-权限关系
    void insertNewRelationship(@Param("roleId") Integer roleId, @Param("authIdList") List<Integer> authIdList);

    // 根据adminId获取已经分配的权限值
    List<String> selectAssignedAuthNameByAdminId(Integer adminId);
}