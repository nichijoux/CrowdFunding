package com.zh.crowd.controller;

import com.github.pagehelper.PageInfo;
import com.zh.crowd.entity.Role;
import com.zh.crowd.result.Result;
import com.zh.crowd.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @ApiOperation(value = "分页查询角色列表")
    @RequestMapping("pageQueryRole")
    public Object pageQueryRole(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "keyword", defaultValue = "") String keyword) {
        PageInfo<Role> pageInfo = roleService.pageQueryRole(pageNum, pageSize, keyword);
        return Result.success(pageInfo);
    }

    @ApiOperation(value = "添加角色")
    @RequestMapping("addRole")
    public Result addRole(
            @RequestParam("roleName") String roleName) {
        roleService.addRole(new Role(null, roleName));
        return Result.success();
    }

    @ApiOperation(value = "更新角色信息")
    @RequestMapping("updateRole")
    public Result updateRole(
            @RequestParam("id") Integer id,
            @RequestParam("roleName") String roleName) {
        roleService.updateRole(new Role(id, roleName));
        return Result.success();
    }

    @ApiOperation(value = "删除角色信息")
    @RequestMapping("deleteRole")
    public Result deleteRole(
            @RequestBody List<Integer> roleIdList) {
        roleService.deleteRole(roleIdList);
        return Result.success();
    }
}
