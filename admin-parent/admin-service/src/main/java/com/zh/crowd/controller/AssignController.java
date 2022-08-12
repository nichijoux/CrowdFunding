package com.zh.crowd.controller;

import com.zh.crowd.entity.Auth;
import com.zh.crowd.entity.Role;
import com.zh.crowd.result.Result;
import com.zh.crowd.service.AdminService;
import com.zh.crowd.service.AuthService;
import com.zh.crowd.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/assign")
public class AssignController {
    private AdminService adminService;

    private RoleService roleService;

    private AuthService authService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @ApiOperation(value = "前往admin的角色分配页面")
    @RequestMapping("toAssignPage")
    public String toAssignPage(
            @RequestParam("adminId") Integer adminId,
            ModelMap modelMap) {
        // 查询已分配角色
        List<Role> assignedRoleList = roleService.getAssignedRole(adminId);
        // 查询未分配的角色
        List<Role> unAssignedRoleList = roleService.getUnAssignedRole(adminId);
        // 存入模型
        modelMap.addAttribute("assignedRoleList", assignedRoleList);
        modelMap.addAttribute("unAssignedRoleList", unAssignedRoleList);
        return "admin/assign";
    }

    @ApiOperation(value = "给admin分配相关角色")
    @RequestMapping("assignRole")
    public String assignRole(
            @RequestParam("adminId") Integer adminId,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("keyword") String keyword,
            @RequestParam(value = "roleIdList", required = false) List<Integer> roleIdList) {
        adminService.assignRole(adminId, roleIdList);
        return "redirect:/admin/pageQueryAdmin?pageNum=" + pageNum
                + "&keyword=" + keyword;
    }

    @ApiOperation(value = "获取所有的权限值信息")
    @RequestMapping("getAllAuth")
    @ResponseBody
    public Result getAllAuth() {
        List<Auth> authList = authService.getAllAuth();
        return Result.success(authList);
    }

    @ApiOperation(value = "根据角色id获取所有的分配的权限id")
    @RequestMapping("getAssignAuthId")
    @ResponseBody
    public Result getAssignAuthIdByRoleId(
            @RequestParam("roleId") Integer roleId) {
        List<Integer> authIdList = authService.getAssignedAuthIdByRoleId(roleId);
        return Result.success(authIdList);
    }

    @ApiOperation(value = "分配角色相关权限")
    @RequestMapping("assignRoleAuth")
    @ResponseBody
    public Result assignRoleAuth(
            @RequestBody Map<String, List<Integer>> map) {
        authService.assignRoleAuth(map);
        return Result.success();
    }
}
