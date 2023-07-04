package com.asm.patuan.service.impl;

import com.asm.patuan.entity.Material;
import com.asm.patuan.repository.MaterialRepository;
import com.asm.patuan.request.MaterialRequest;
import com.asm.patuan.response.MaterialReponse;
import com.asm.patuan.service.Behavior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialServiceImpl implements Behavior<MaterialReponse, MaterialRequest, Long> {

    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public Page<MaterialReponse> getAllPage(Integer pageNo, Integer size) {
        return materialRepository.getAllPage(PageRequest.of(pageNo, size));
    }

    @Override
    public List<MaterialReponse> getAll() {
        return materialRepository.getAll();
    }

    @Override
    public MaterialRequest getOne(Long aLong) {
        return materialRepository.findOne(aLong);
    }

    @Override
    public MaterialReponse getOneT(Long aLong) {
        return null;
    }

    @Override
    public void saveOrUpdate(MaterialRequest materialRequest) {
        Material material = Material.builder()
                .id(materialRequest.getId())
                .name(materialRequest.getName())
                .build();
        materialRepository.save(material);
    }

    @Override
    public void remove(Long aLong) {
        materialRepository.deleteById(aLong);
    }
}
