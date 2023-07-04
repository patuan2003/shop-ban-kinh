package com.asm.patuan.service.impl;

import com.asm.patuan.entity.Brand;
import com.asm.patuan.repository.BrandRepository;
import com.asm.patuan.request.BrandRequest;
import com.asm.patuan.response.BrandReponse;
import com.asm.patuan.service.Behavior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements Behavior<BrandReponse, BrandRequest, Long> {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Page<BrandReponse> getAllPage(Integer pageNo, Integer size) {
        return brandRepository.getAllPage(PageRequest.of(pageNo, size));
    }

    @Override
    public List<BrandReponse> getAll() {
        return brandRepository.getAll();
    }

    @Override
    public BrandRequest getOne(Long aLong) {
        return brandRepository.findOne(aLong);
    }

    @Override
    public BrandReponse getOneT(Long aLong) {
        return null;
    }

    @Override
    public void saveOrUpdate(BrandRequest brandRequest) {
        Brand brand = Brand.builder()
                .id(brandRequest.getId())
                .name(brandRequest.getName())
                .build();
        brandRepository.save(brand);
    }

    @Override
    public void remove(Long aLong) {
        Brand brand = Brand.builder()
                .id(brandRepository.findOne(aLong).getId())
                .build();
        brandRepository.delete(brand);
    }
}
