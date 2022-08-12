package com.zh.crowd.security;

import com.zh.crowd.entity.Admin;
import com.zh.crowd.entity.Role;
import com.zh.crowd.service.AdminService;
import com.zh.crowd.service.AuthService;
import com.zh.crowd.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Spring Security 数据库中获取用户数据
 */
@Component
public class CrowdUserDetailsService implements UserDetailsService {
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

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        // 1.根据账号名称查询Admin对象
        Admin admin = adminService.getAdminByAccount(account);
        // 2.获取账号adminId
        Integer adminId = admin.getId();
        // 3.根据adminId查询角色信息
        List<Role> assignedRoleList = roleService.getAssignedRole(adminId);
        // 4.根据 adminId 查询权限信息
        List<String> authNameList = authService.getAssignedAuthName(adminId);
        // 5.创建集合用来存储 GrantedAuthority
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 6.遍历 assignedRoleList 存入角色信息
        assignedRoleList.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        // 7.遍历 authNameList 存入权限信息
        authNameList.forEach(authName -> {
            authorities.add(new SimpleGrantedAuthority(authName));
        });
        // 8.封装 SecurityAdmin 对象
        return new SecurityAdmin(admin, authorities);
    }
}
