package com.zh.crowd.service;

import com.zh.crowd.entity.Menu;

import java.util.List;

public interface MenuService {
    // 保存菜单
    void addMenu(Menu menu);

    // 修改菜单
    void updateMenu(Menu menu);

    // 删除菜单
    void deleteMenu(Integer id);

    // 获取所有的菜单
    List<Menu> getAllMenu();
}
