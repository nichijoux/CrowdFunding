package com.zh.crowd.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    String uploadOssFile(MultipartFile file);
}
