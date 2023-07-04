package com.asm.patuan.repository;

import com.asm.patuan.entity.OrderDetail;
import com.asm.patuan.request.CancelOrderRequest;
import com.asm.patuan.response.AllOrderResponse;
import com.asm.patuan.response.HistoryResponse;
import com.asm.patuan.response.ThongKeReponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    @Query("SELECT new com.asm.patuan.response.HistoryResponse(o.id,p.name,p.image,o.orderStatus,od.quantity,od.price)" +
            " FROM OrderDetail od JOIN od.order o JOIN od.product p" +
            " WHERE o.userId =?1 " +
            " ORDER BY o.id desc")
    List<HistoryResponse> history(String id);

    @Query("SELECT new com.asm.patuan.response.HistoryResponse(o.id,p.name,p.image,o.orderStatus,od.quantity,od.price)" +
            " FROM OrderDetail od JOIN od.order o JOIN od.product p" +
            " WHERE o.customer.id =?1 " +
            " ORDER BY o.orderStatus desc")
    List<HistoryResponse> historyCustomer(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Order o set o.orderStatus = 4 WHERE o.id = ?1")
    void updateOrderStatus(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Order o set o.orderStatus = 3, o.payDate = current_date WHERE o.id = ?1")
    void updateOrderStatusDaNhan(Long id);

    @Query("SELECT new com.asm.patuan.response.AllOrderResponse(od.id,o.id,o.customer.id,o.userId,p.name,p.image,o.orderStatus" +
            ",od.quantity,od.price, o.recipientName)" +
            " FROM OrderDetail od JOIN od.order o JOIN od.product p" +
            " ORDER BY o.id desc")
    Page<AllOrderResponse> getAllOrder(Pageable pageable);


    @Query("SELECT new com.asm.patuan.response.ThongKeReponse(od.product.id,od.product.name,sum(od.quantity))" +
            " FROM OrderDetail od " +
            " GROUP BY od.product.id, od.product.name" +
            " ORDER BY sum(od.quantity) DESC" +
            " LIMIT 5")
    List<ThongKeReponse> thongKe();

    @Query("SELECT new com.asm.patuan.request.CancelOrderRequest(od.id, o.id, p.id, p.quantity, od.quantity)" +
            " FROM OrderDetail od join od.order o join od.product p" +
            " WHERE o.id = ?1")
    List<CancelOrderRequest> getCancelOrder(Long orderId);

}
