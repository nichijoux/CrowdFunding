package com.zh.crowd.controller;

import com.zh.crowd.client.MySqlClient;
import com.zh.crowd.client.RedisClient;
import com.zh.crowd.constant.CrowdConstant;
import com.zh.crowd.entity.vo.DetailProjectVO;
import com.zh.crowd.entity.vo.ProjectVO;
import com.zh.crowd.service.OssService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProjectController {
    private MySqlClient mySqlClient;

    private RedisClient redisClient;

    private OssService ossService;

    @Autowired
    public void setOssService(OssService ossService) {
        this.ossService = ossService;
    }

    @Autowired
    public void setMySqlClient(MySqlClient mySqlClient) {
        this.mySqlClient = mySqlClient;
    }

    @Autowired
    public void setRedisClient(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    @ApiOperation(value = "获取项目详情")
    @RequestMapping("getProjectDetails/{projectId}")
    public String getProjectDetails(
            @PathVariable Integer projectId,
            Model model) {
        DetailProjectVO detailProjectVO = mySqlClient.remoteGetProjectDetails(projectId);
        model.addAttribute("detailProjectVO", detailProjectVO);
        return "project/detail";
    }

    @RequestMapping("saveProjectBasicInfo")
    public String saveProjectBasicInfo(
            ProjectVO projectVO,
            MultipartFile headerPicture,
            List<MultipartFile> detailPictureList,
            HttpSession session,
            ModelMap modelMap) {
        // 一、完成头图上传
        // 1.判断头图是否为空
        boolean headerPictureIsEmpty = true;

        if (headerPicture != null) {
            headerPictureIsEmpty = headerPicture.isEmpty();
        }

        if (headerPictureIsEmpty) {
            // 2.如果没有上传头图则返回到表单页面并显示错误消息
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_HEADER_PIC_EMPTY);
            return "project/launch";
        }

        // 3.如果用户确实上传了有内容的文件， 则执行上传
        String url = ossService.uploadOssFile(headerPicture);
        // 4.判断头图是否上传成功
        if (StringUtils.hasText(url)) {
            // 5.成功则获取图片访问路径,存入ProjectVO对象中
            projectVO.setHeaderPicturePath(url);
        } else {
            // 7.上传失败则返回上传页面，返回错误消息
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_HEADER_PIC_UPLOAD_FAILED);
            return "project/launch";
        }

        // 二、详情图片上传

        // 1.创建一个用来存放详情图片路径的集合
        List<String> detailPicturePathList = new ArrayList<String>();

        // 2.检查 detailPictureList 是否有效
        if (detailPictureList == null || detailPictureList.size() == 0) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_DETAIL_PIC_EMPTY);
            return "project/launch";
        }

        // 3.遍历 detailPictureList 集合
        for (MultipartFile detailPicture : detailPictureList) {
            // 4.判断是否为空
            if (detailPicture.isEmpty()) {
                // 5.检测到详情图片中单个文件为空也是回去显示错误消息
                modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_DETAIL_PIC_EMPTY);
                return "project/launch";
            }

            // 6.执行上传
            String detailPictureUrl = ossService.uploadOssFile(detailPicture);

            // 7.检查上传结果
            if (StringUtils.hasText(detailPictureUrl)) {
                // 8.收集刚刚上传的图片的访问路径
                detailPicturePathList.add(detailPictureUrl);
            } else {
                // 9.如果上传失败则返回到表单页面并显示错误消息
                modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_DETAIL_PIC_UPLOAD_FAILED);
                return "project/launch";
            }
        }
        // 10.将存放了详情图片访问路径的集合存入 ProjectVO 中
        projectVO.setDetailPicturePathList(detailPicturePathList);
        // 三、 后续操作
        // 1.将 ProjectVO 对象存入 Session 域
        session.setAttribute(CrowdConstant.ATTR_NAME_TEMPLE_PROJECT, projectVO);
        // 2.以完整的访问路径前往下一个收集回报信息的页面
        return "redirect:http://localhost:8500/project/toReturnInfoPage";
    }
}
