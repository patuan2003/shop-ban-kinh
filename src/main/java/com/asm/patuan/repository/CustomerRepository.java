package com.asm.patuan.repository;

import com.asm.patuan.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByAccount(String account);

    Boolean existsByAccount(String account);

}
