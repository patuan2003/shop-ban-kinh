package com.asm.patuan.service;

import com.asm.patuan.entity.Cart;

public interface CartService {

    void save(Cart cart);

    void delete(Long id);

}
