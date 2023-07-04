package com.asm.patuan.service.impl;

import com.asm.patuan.entity.Color;
import com.asm.patuan.repository.ColorRepository;
import com.asm.patuan.request.ColorRequest;
import com.asm.patuan.response.ColorResponse;
import com.asm.patuan.service.Behavior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServiceImpl implements Behavior<ColorResponse, ColorRequest, Long> {

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public Page<ColorResponse> getAllPage(Integer pageNo, Integer size) {
        return colorRepository.getAllPage(PageRequest.of(pageNo, size));
    }

    @Override
    public List<ColorResponse> getAll() {
        return colorRepository.getAll();
    }

    @Override
    public ColorRequest getOne(Long aLong) {
        return colorRepository.findOne(aLong);
    }

    @Override
    public ColorResponse getOneT(Long aLong) {
        return null;
    }

    @Override
    public void saveOrUpdate(ColorRequest request) {
        Color color = Color.builder()
                .id(request.getId())
                .name(request.getName())
                .build();
        colorRepository.save(color);
    }

    @Override
    public void remove(Long aLong) {
        colorRepository.deleteById(aLong);
    }
}
