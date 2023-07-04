package com.asm.patuan.service.impl;

import com.asm.patuan.entity.Customer;
import com.asm.patuan.repository.CustomerRepository;
import com.asm.patuan.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public Optional<Customer> getOne(String account) {
        return repository.findByAccount(account);
    }

    @Override
    public Optional<Customer> getId(Long id) {
        return repository.findById(id);
    }
}
