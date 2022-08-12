package com.zh.crowd.service.impl;

import com.zh.crowd.mapper.ProjectMapper;
import com.zh.crowd.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {
    private ProjectMapper baseMapper;

    @Autowired
    public void setBaseMapper(ProjectMapper baseMapper) {
        this.baseMapper = baseMapper;
    }
}
