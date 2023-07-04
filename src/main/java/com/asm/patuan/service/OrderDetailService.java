package com.asm.patuan.service;

import com.asm.patuan.entity.OrderDetail;
import com.asm.patuan.entity.Product;
import com.asm.patuan.request.CancelOrderRequest;
import com.asm.patuan.response.AllOrderResponse;
import com.asm.patuan.response.HistoryResponse;
import com.asm.patuan.response.ThongKeReponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderDetailService {

    void save(OrderDetail order);

    Product getProduct(Long id);

    void updateQuantity(Integer quantity, Long id);

    List<HistoryResponse> getHistory(String id);

    List<HistoryResponse> getHistoryCustomer(Long id);

    void updateOrderStatus(Long id);

    Page<AllOrderResponse> getAllOrder(Integer page, Integer size);

    void updateStatusDaNhan(Long id);

    OrderDetail getOne(Long id);

    List<ThongKeReponse> thongKe();

    List<CancelOrderRequest> getCancelOrder(Long orderId);

}
