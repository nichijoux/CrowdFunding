package com.zh.crowd.service.impl;

import com.zh.crowd.mapper.OrderMapper;
import com.zh.crowd.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderMapper baseMapper;

    @Autowired
    public void setBaseMapper(OrderMapper baseMapper) {
        this.baseMapper = baseMapper;
    }
}
