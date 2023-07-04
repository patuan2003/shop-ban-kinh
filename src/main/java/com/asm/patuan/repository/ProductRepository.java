package com.asm.patuan.repository;

import com.asm.patuan.entity.Product;
import com.asm.patuan.request.ProductRequest;
import com.asm.patuan.response.ProductResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT new com.asm.patuan.response.ProductResponse(p.id,p.name,p.price,p.quantity,p.image) FROM Product p")
    Page<ProductResponse> getAllPage(Pageable pageable);

    @Query("SELECT new com.asm.patuan.response.ProductResponse(p.id,p.name,p.price,p.quantity,p.image)" +
            " FROM Product p")
    List<ProductResponse> getAll();

    @Query("SELECT new com.asm.patuan.request.ProductRequest" +
            "(p.id,p.code,p.name,p.price,p.image,p.quantity,p.description,b.id,manu.id,c.id,m.id,cate.id)" +
            " FROM Product p JOIN p.brand b JOIN p.manufacturer manu JOIN p.color c " +
            "JOIN p.material m JOIN p.category cate " +
            "WHERE p.id =?1")
    ProductRequest findOne(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Product u SET u.quantity = ?1 WHERE u.id = ?2")
    void updateQuantity(Integer quantity, Long userId);

    @Query("SELECT new com.asm.patuan.response.ProductResponse(p.id,p.name,p.price,p.quantity,p.image) " +
            "FROM Product p " +
            " WHERE  (?1 IS NULL OR p.category.id = ?1) AND (?2 IS NULL OR p.color.id = ?2)" +
            " AND (?3 IS NULL OR p.material.id = ?3 ) AND (?4 IS NULL OR p.manufacturer.id = ?4)" +
            " AND (?5 IS NULL OR p.brand.id =?5) ")
    Page<ProductResponse> filterProduct(Long cateId, Long colorId, Long materialId, Long manuId, Long brandId, Pageable pageable);

}
