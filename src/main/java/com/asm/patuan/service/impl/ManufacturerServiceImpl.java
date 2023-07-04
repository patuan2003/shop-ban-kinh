package com.asm.patuan.service.impl;

import com.asm.patuan.entity.Manufacturer;
import com.asm.patuan.repository.ManufacturerRepository;
import com.asm.patuan.request.ManufacturerRequest;
import com.asm.patuan.response.ManufacturerReponse;
import com.asm.patuan.service.Behavior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements Behavior<ManufacturerReponse, ManufacturerRequest, Long> {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Override
    public Page<ManufacturerReponse> getAllPage(Integer pageNo, Integer size) {
        return manufacturerRepository.getAllPage(PageRequest.of(pageNo, size));
    }

    @Override
    public List<ManufacturerReponse> getAll() {
        return manufacturerRepository.getAll();
    }

    @Override
    public ManufacturerRequest getOne(Long aLong) {
        return manufacturerRepository.findOne(aLong);
    }

    @Override
    public ManufacturerReponse getOneT(Long aLong) {
        return null;
    }

    @Override
    public void saveOrUpdate(ManufacturerRequest manufacturerRequest) {
        Manufacturer manufacturer = Manufacturer.builder()
                .id(manufacturerRequest.getId())
                .name(manufacturerRequest.getName())
                .build();
        manufacturerRepository.save(manufacturer);
    }

    @Override
    public void remove(Long aLong) {
        Manufacturer manufacturer = Manufacturer.builder()
                .id(manufacturerRepository.findOne(aLong).getId())
                .build();
        manufacturerRepository.delete(manufacturer);
    }
}
