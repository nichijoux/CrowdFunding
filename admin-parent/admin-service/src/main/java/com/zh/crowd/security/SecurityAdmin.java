package com.zh.crowd.security;

import com.zh.crowd.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * Spring Security 中的包含权限的Admin实体类
 */
public class SecurityAdmin extends User {
    private static final long serialVersionUID = 1L;

    // 原始的 Admin 对象， 包含 Admin 对象的全部属性
    private final Admin originalAdmin;

    public SecurityAdmin(Admin originalAdmin, List<GrantedAuthority> authorities) {
        super(originalAdmin.getAccount(), originalAdmin.getPassword(), authorities);
        // 给本类的 this.originalAdmin 赋值
        this.originalAdmin = originalAdmin;
        // 将原始 Admin 对象中的密码擦除
        this.originalAdmin.setPassword(null);
    }

    // 对外提供的获取原始 Admin 对象的 getXxx()方法
    public Admin getOriginalAdmin() {
        return originalAdmin;
    }
}
