package com.asm.patuan.service.impl;

import com.asm.patuan.entity.OrderDetail;
import com.asm.patuan.entity.Product;
import com.asm.patuan.repository.OrderDetailRepository;
import com.asm.patuan.repository.ProductRepository;
import com.asm.patuan.request.CancelOrderRequest;
import com.asm.patuan.response.AllOrderResponse;
import com.asm.patuan.response.HistoryResponse;
import com.asm.patuan.response.ThongKeReponse;
import com.asm.patuan.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;


    @Autowired
    private ProductRepository productRepository;

    @Override
    public void save(OrderDetail order) {
        orderDetailRepository.save(order);
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void updateQuantity(Integer quantity, Long id) {
        productRepository.updateQuantity(quantity, id);
    }

    @Override
    public List<HistoryResponse> getHistory(String id) {
        return orderDetailRepository.history(id);
    }

    @Override
    public List<HistoryResponse> getHistoryCustomer(Long id) {
        return orderDetailRepository.historyCustomer(id);
    }

    @Override
    public void updateOrderStatus(Long id) {
        orderDetailRepository.updateOrderStatus(id);
    }

    @Override
    public Page<AllOrderResponse> getAllOrder(Integer page, Integer size) {
        return orderDetailRepository.getAllOrder(PageRequest.of(page, size));
    }

    @Override
    public void updateStatusDaNhan(Long id) {
        orderDetailRepository.updateOrderStatusDaNhan(id);
    }

    @Override
    public OrderDetail getOne(Long id) {
        return orderDetailRepository.findById(id).get();
    }

    @Override
    public List<ThongKeReponse> thongKe() {
        return orderDetailRepository.thongKe();
    }

    @Override
    public List<CancelOrderRequest> getCancelOrder(Long orderId) {
        return orderDetailRepository.getCancelOrder(orderId);
    }

}
