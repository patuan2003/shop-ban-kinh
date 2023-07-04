package com.asm.patuan.service;

import com.asm.patuan.entity.Cart;
import com.asm.patuan.entity.CartDetail;
import com.asm.patuan.entity.Product;
import com.asm.patuan.request.CartDetailRequest;
import com.asm.patuan.response.CartDetailResponse;

import java.util.List;

public interface CartDetailService {

    void save(CartDetailRequest request);

    List<CartDetail> getAll();

    Product getProduct(Long id);

    Cart getCart(Long id);

    List<CartDetailResponse> getCartUser(String id);

    List<CartDetailResponse> getAllCustomerId(Long id);

    List<CartDetailRequest> getCartDetailCustomerId(Long id);

    CartDetailResponse findByUserAndProduct(String user, Long product);

    CartDetailResponse findByCustomerAndProduct(Long customer, Long product);

    CartDetailRequest getCartDetail(Long id);

    void delete(Long id);

    List<CartDetailRequest> getCartDetailRequest(String id);

    List<CartDetailRequest> getCartDetailCustomer(Long id);

    void remove(Long id);

}
