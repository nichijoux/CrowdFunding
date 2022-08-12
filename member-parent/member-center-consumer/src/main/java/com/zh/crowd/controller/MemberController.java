package com.zh.crowd.controller;

import com.zh.crowd.client.MySqlClient;
import com.zh.crowd.client.RedisClient;
import com.zh.crowd.constant.CrowdConstant;
import com.zh.crowd.entity.Member;
import com.zh.crowd.entity.vo.MemberLoginVO;
import com.zh.crowd.entity.vo.MemberVO;
import com.zh.crowd.result.Result;
import com.zh.crowd.utils.CrowdUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class MemberController {
    private MySqlClient mySqlClient;

    private RedisClient redisClient;

    @Autowired
    public void setMySqlClient(MySqlClient mySqlClient) {
        this.mySqlClient = mySqlClient;
    }

    @Autowired
    public void setRedisClient(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    @ApiOperation(value = "登录")
    @RequestMapping("login")
    public String login(
            @RequestParam("account") String account,
            @RequestParam("password") String password,
            ModelMap modelMap,
            HttpSession session) {
        // 调用远程接口获取账号
        Member member = mySqlClient.remoteGetMemberByAccount(account);
        // 失败则返回登录界面
        if (member == null) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_EXCEPTION, CrowdConstant.MESSAGE_LOGIN_FAILED);
            return "redirect:/member/toLoginPage";
        }
        // 比较密码
        String databasePassword = member.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(password, databasePassword)) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_EXCEPTION, CrowdConstant.MESSAGE_LOGIN_FAILED);
            return "redirect:/member/toLoginPage";
        }
        // 创建memberLoginVO对象存入session域
        MemberLoginVO loginVO = new MemberLoginVO();
        BeanUtils.copyProperties(member, loginVO);
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_MEMBER, loginVO);
        return "redirect:http://localhost:8500/member/toCenterPage";
    }

    @ApiOperation(value = "用户注册")
    @RequestMapping("register")
    public String register(MemberVO memberVO, ModelMap modelMap) {
        // 1.获取用户输入的手机号
        String phoneNum = memberVO.getPhoneNum();
        // 2.拼接Redis中存储的Key
        String key = CrowdConstant.REDIS_CODE_PREFIX + phoneNum;
        // 3.从Redis读取Key对应的value
        Result redisResult = redisClient.remoteGetRedisValueByKey(key);
        // 4.检查查询操作是否有效
        if (redisResult.getCode() != 200) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, redisResult.getMessage());
            return "redirect:/member/toRegisterPage";
        }
        String redisCode = (String) redisResult.getData();
        if (redisCode == null) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_CODE_NOT_EXISTS);
            return "redirect:/member/toRegisterPage";
        }

        // 5.能从Redis查询到，则比较两者是否一致
        String formCode = memberVO.getCode();
        if (!formCode.equals(redisCode)) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_CODE_INVALID);
        }
        // 6.一致，将value从Redis中删除
        redisClient.remoteDeleteRedisKey(key);
        // 7.执行密码加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
        // 8. 执行保存
        Member member = new Member();
        BeanUtils.copyProperties(memberVO, member);
        Result addResult = mySqlClient.remoteAddMember(member);
        if (addResult.getCode() != 200) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, addResult.getMessage());
            return "redirect:/member/toRegisterPage";
        }
        return "redirect:/member/toLoginPage";
    }

    @ApiOperation(value = "登出")
    @RequestMapping("logout")
    public String login(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @ApiOperation(value = "发送验证码")
    @RequestMapping("sendMessage")
    @ResponseBody
    public Result sendMessage(
            @RequestParam("phoneNum") String phoneNum) {
        // 发送验证码(不使用腾讯云了,直接发送)
        String value = CrowdUtils.getSixBitRandom();
        // 判断是否发送成功(直接存入redis)
        String key = CrowdConstant.REDIS_CODE_PREFIX + phoneNum;
        Result result = redisClient.remoteSetRedisKeyValue(key, value);
        if (result.getCode() == 200) {
            return Result.success();
        } else {
            return result;
        }
    }
}
