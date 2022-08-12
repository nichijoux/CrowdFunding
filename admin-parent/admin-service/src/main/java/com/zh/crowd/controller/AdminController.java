package com.zh.crowd.controller;

import com.github.pagehelper.PageInfo;
import com.zh.crowd.constant.CrowdConstant;
import com.zh.crowd.entity.Admin;
import com.zh.crowd.service.AdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @ApiOperation(value = "分页查询admin用户")
    @RequestMapping("pageQueryAdmin")
    public String pageQueryAdmin(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            ModelMap modelMap) {
        PageInfo<Admin> pageInfo = adminService.pageQueryAdmin(pageNum, pageSize, keyword);
        // 将pageInfo存入模型
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO, pageInfo);
        return "admin/page";
    }

    @ApiOperation(value = "添加admin用户")
    @RequestMapping("addAdmin")
    public String addAdmin(Admin admin) {
        adminService.saveAdmin(admin);
        return "redirect:/admin/pageQueryAdmin?pageNum=" + Integer.MAX_VALUE;
    }

    @ApiOperation(value = "跳转到修改admin的页面")
    @RequestMapping("toUpdatePage")
    public String toEditPage(
            @RequestParam("adminId") Integer adminId,
            ModelMap modelMap) {
        Admin admin = adminService.getAdmin(adminId);
        modelMap.addAttribute("admin", admin);
        return "admin/update";
    }

    @ApiOperation(value = "更新admin用户信息")
    @RequestMapping("updateAdmin")
    public String updateAdmin(
            Admin admin,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "keyword", defaultValue = "") String keyword) {
        adminService.updateAdmin(admin);
        return "redirect:/admin/pageQueryAdmin?pageNum=" + pageNum
                + "&pageSize=" + pageSize + "&keyword=" + keyword;
    }

    @ApiOperation(value = "删除admin用户")
    @RequestMapping("deleteAdmin")
    public String deleteAdmin(
            @RequestParam("adminId") Integer adminId,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            HttpSession session) {
        // 判断是否为当前用户
        // 执行删除
        adminService.deleteAdmin(adminId);
        session.setAttribute("msg", "用户已被删除");
        // 页面跳转：回到分页页面
        return "redirect:/admin/pageQueryAdmin?pageNum=" + pageNum + "&keyword=" + keyword;
    }
}
