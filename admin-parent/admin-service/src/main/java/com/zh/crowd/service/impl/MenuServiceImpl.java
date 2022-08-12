package com.zh.crowd.service.impl;

import com.zh.crowd.entity.Menu;
import com.zh.crowd.entity.MenuExample;
import com.zh.crowd.mapper.MenuMapper;
import com.zh.crowd.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    private MenuMapper baseMapper;

    @Autowired
    public void setBaseMapper(MenuMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 保存菜单
     *
     * @param menu 要保存的菜单实体
     */
    @Override
    public void addMenu(Menu menu) {
        baseMapper.insert(menu);
    }

    /**
     * 修改菜单
     *
     * @param menu 要修改的菜单实体
     */
    @Override
    public void updateMenu(Menu menu) {
        baseMapper.updateByPrimaryKeySelective(menu);
    }

    /**
     * 删除菜单
     *
     * @param id 要删除的菜单id
     */
    @Override
    public void deleteMenu(Integer id) {
        baseMapper.deleteByPrimaryKey(id);
    }

    /**
     * 获取所有的菜单
     *
     * @return 菜单列表
     */
    @Override
    public List<Menu> getAllMenu() {
        return baseMapper.selectByExample(new MenuExample());
    }
}
