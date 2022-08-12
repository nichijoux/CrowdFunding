package com.zh.crowd.utils;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.Random;

public class CrowdUtils {
    private static final DecimalFormat codeFormat = new DecimalFormat("000000");

    /**
     * 判断当前请求是否为Ajax请求
     *
     * @param request 请求对象
     * @return true：当前请求为Ajax请求
     * false：当前请求不是Ajax请求
     */
    public static boolean judgeRequestType(HttpServletRequest request) {
        // 1. 获取请求头
        String acceptHeader = request.getHeader("Accept");
        String xRequestHeader = request.getHeader("X-Request-With");

        // 2. 判断
        return (acceptHeader != null && acceptHeader.contains("application/json"))
                ||
                (xRequestHeader != null && xRequestHeader.equals("XMLHttpRequest"));
    }

    /**
     * 获取6位的手机验证码
     *
     * @return 6位的手机验证码
     */
    public static String getSixBitRandom() {
        Random random = new Random();
        return codeFormat.format(random.nextInt(1000000));
    }
}
