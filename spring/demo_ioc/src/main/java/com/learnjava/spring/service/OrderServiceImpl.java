package com.learnjava.spring.service;

import com.learnjava.spring.mapper.OrderMapper;

public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public void complexInsert() throws InterruptedException {
        System.out.println("service complex insert called...");
        System.out.println("using mapper to implement...");
        orderMapper.insert();
        System.out.println("Done! Quit service...");
    }
}
