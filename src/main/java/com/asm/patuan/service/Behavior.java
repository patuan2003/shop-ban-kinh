package com.asm.patuan.service;

import org.springframework.data.domain.Page;

import java.util.List;

public interface Behavior<T, U, V> {

    Page<T> getAllPage(Integer pageNo, Integer size);

    List<T> getAll();

    U getOne(V v);

    T getOneT(V v);

    void saveOrUpdate(U u);

    void remove(V v);

}
