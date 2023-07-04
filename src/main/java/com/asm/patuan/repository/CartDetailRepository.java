package com.asm.patuan.repository;

import com.asm.patuan.entity.CartDetail;
import com.asm.patuan.request.CartDetailRequest;
import com.asm.patuan.response.CartDetailResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {

    @Query("SELECT new com.asm.patuan.response.CartDetailResponse(cd.id,p.id,p.image,cate.name,p.name,cd.quantity,cd.price,c.id) FROM CartDetail cd " +
            "JOIN cd.cart c " +
            "JOIN cd.product p " +
            "JOIN p.category cate " +
            "WHERE c.UserCartId = ?1 ")
    List<CartDetailResponse> getUserId(String id);

    @Query("SELECT new com.asm.patuan.response.CartDetailResponse(cd.id,p.id,p.image,cate.name,p.name,cd.quantity,cd.price,c.id) FROM CartDetail cd " +
            "JOIN cd.cart c " +
            "JOIN cd.product p " +
            "JOIN p.category cate " +
            "WHERE c.customer.id = ?1 ")
    List<CartDetailResponse> getAllCustomer(Long id);

    @Query("SELECT new com.asm.patuan.response.CartDetailResponse(cd.id,p.id,p.image,cate.name,p.name,cd.quantity,cd.price,c.id) FROM CartDetail cd " +
            "JOIN cd.cart c " +
            "JOIN cd.product p " +
            "JOIN p.category cate " +
            "WHERE c.UserCartId = ?1 AND cd.product.id = ?2")
    CartDetailResponse findByUserAndProduct(String user, Long product);

    @Query("SELECT new com.asm.patuan.response.CartDetailResponse(cd.id,p.id,p.image,cate.name,p.name,cd.quantity,cd.price,c.id) FROM CartDetail cd " +
            "JOIN cd.cart c " +
            "JOIN cd.product p " +
            "JOIN p.category cate " +
            "WHERE c.customer.id = ?1 AND cd.product.id = ?2")
    CartDetailResponse findByCustomerAndProduct(Long user, Long product);

    @Query("SELECT new com.asm.patuan.request.CartDetailRequest(cd.id,c.id,p.id,cd.quantity,cd.price) FROM CartDetail cd " +
            "JOIN cd.cart c " +
            "JOIN cd.product p " +
            "JOIN p.category cate " +
            "WHERE cd.id = ?1")
    CartDetailRequest findId(Long id);

    @Query("SELECT new com.asm.patuan.request.CartDetailRequest(cd.id,c.id,p.id,cd.quantity,cd.price) FROM CartDetail cd " +
            "JOIN cd.cart c " +
            "JOIN cd.product p " +
            "JOIN p.category cate " +
            "WHERE c.UserCartId = ?1")
    List<CartDetailRequest> getCartDetailRequest(String id);

    @Query("SELECT new com.asm.patuan.request.CartDetailRequest(cd.id,c.id,p.id,cd.quantity,cd.price) FROM CartDetail cd " +
            "JOIN cd.cart c " +
            "JOIN cd.product p " +
            "JOIN p.category cate " +
            "WHERE c.customer.id = ?1")
    List<CartDetailRequest> getCartDetailCustomerId(Long id);
}
