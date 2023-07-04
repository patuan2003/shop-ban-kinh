package com.asm.patuan.service.impl;

import com.asm.patuan.entity.Cart;
import com.asm.patuan.entity.CartDetail;
import com.asm.patuan.entity.Product;
import com.asm.patuan.repository.CartDetailRepository;
import com.asm.patuan.repository.CartRepository;
import com.asm.patuan.repository.ProductRepository;
import com.asm.patuan.request.CartDetailRequest;
import com.asm.patuan.response.CartDetailResponse;
import com.asm.patuan.service.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartDetailServiceImpl implements CartDetailService {

    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public void save(CartDetailRequest request) {
        CartDetail cartDetail = CartDetail.builder()
                .id(request.getId())
                .price(request.getPrice())
                .cart(cartRepository.findById(request.getCartId()).get())
                .product(productRepository.findById(request.getProductId()).get())
                .quantity(request.getQuantity())
                .build();
        cartDetailRepository.save(cartDetail);
    }

    @Override
    public List<CartDetail> getAll() {
        return cartDetailRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Cart getCart(Long id) {
        return cartRepository.findById(id).get();
    }


    @Override
    public List<CartDetailResponse> getCartUser(String id) {
        return cartDetailRepository.getUserId(id);
    }

    @Override
    public List<CartDetailResponse> getAllCustomerId(Long id) {
        return cartDetailRepository.getAllCustomer(id);
    }

    @Override
    public List<CartDetailRequest> getCartDetailCustomerId(Long id) {
        return cartDetailRepository.getCartDetailCustomerId(id);
    }

    @Override
    public CartDetailResponse findByUserAndProduct(String user, Long product) {
        return cartDetailRepository.findByUserAndProduct(user, product);
    }

    @Override
    public CartDetailResponse findByCustomerAndProduct(Long customer, Long product) {
        return cartDetailRepository.findByCustomerAndProduct(customer, product);
    }

    @Override
    public CartDetailRequest getCartDetail(Long id) {
        return cartDetailRepository.findId(id);
    }

    @Override
    public void delete(Long id) {
        cartDetailRepository.deleteById(id);
    }


    @Override
    public List<CartDetailRequest> getCartDetailRequest(String id) {
        return cartDetailRepository.getCartDetailRequest(id);
    }

    @Override
    public List<CartDetailRequest> getCartDetailCustomer(Long id) {
        return cartDetailRepository.getCartDetailCustomerId(id);
    }

    @Override
    public void remove(Long id) {
        cartDetailRepository.deleteById(id);
    }

}
