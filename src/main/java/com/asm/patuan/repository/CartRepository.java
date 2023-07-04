package com.asm.patuan.repository;

import com.asm.patuan.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT c FROM Cart c JOIN c.customer cus " +
            "WHERE cus.id = ?1")
    Cart findByCustomerId(Long id);

}
