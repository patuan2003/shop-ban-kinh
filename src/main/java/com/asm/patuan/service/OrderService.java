package com.asm.patuan.service;

import com.asm.patuan.entity.Order;

public interface OrderService {

    void save(Order order);

    void updateStatusConfirm(Long id);

    Order getOne(Long id);

}
