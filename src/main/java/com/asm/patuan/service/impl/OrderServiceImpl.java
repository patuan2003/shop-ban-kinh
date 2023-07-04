package com.asm.patuan.service.impl;

import com.asm.patuan.entity.Order;
import com.asm.patuan.repository.OrderRepository;
import com.asm.patuan.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void updateStatusConfirm(Long id) {
        orderRepository.updateOrderStatusConfirm(id);
    }

    @Override
    public Order getOne(Long id) {
        return orderRepository.findById(id).get();
    }
}
