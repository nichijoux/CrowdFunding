package com.zh.crowd.controller;

import com.zh.crowd.entity.Menu;
import com.zh.crowd.result.Result;
import com.zh.crowd.service.MenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menu")
public class MenuController {
    private MenuService menuService;

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @ApiOperation(value = "获取树型菜单")
    @RequestMapping("getMenuTree")
    public Result getMenuTree() {
        // 查询所有的menu菜单
        List<Menu> menuList = menuService.getAllMenu();
        // 声明根节点
        final Menu[] root = {null};
        // 查找父节点的map
        Map<Integer, Menu> menuMap = menuList.stream().collect(Collectors.toMap(Menu::getId, menu -> menu));
        // 组装父节点
        menuList.forEach(menu -> {
            Integer pid = menu.getPid();
            if (pid == null) {
                root[0] = menu;
            } else {
                menuMap.get(pid).getChildren().add(menu);
            }
        });
        return Result.success(root[0]);
    }

    @ApiOperation(value = "保存菜单")
    @RequestMapping("addMenu")
    public Result addMenu(Menu menu) {
        menuService.addMenu(menu);
        return Result.success();
    }

    @ApiOperation(value = "修改菜单")
    @RequestMapping("updateMenu")
    public Result updateMenu(Menu menu) {
        menuService.updateMenu(menu);
        return Result.success();
    }

    @ApiOperation(value = "删除菜单")
    @RequestMapping("deleteMenu")
    public Result deleteMenu(@RequestParam("id") Integer id) {
        menuService.deleteMenu(id);
        return Result.success();
    }
}
