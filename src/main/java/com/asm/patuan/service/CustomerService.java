package com.asm.patuan.service;

import com.asm.patuan.entity.Customer;

import java.util.Optional;

public interface CustomerService {

    Optional<Customer> getOne(String account);

    Optional<Customer> getId(Long id);

}
