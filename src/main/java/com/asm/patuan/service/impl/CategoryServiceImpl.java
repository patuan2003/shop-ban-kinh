package com.asm.patuan.service.impl;

import com.asm.patuan.entity.Category;
import com.asm.patuan.repository.CategoryRepository;
import com.asm.patuan.request.CategoryRequest;
import com.asm.patuan.response.CategoryReponse;
import com.asm.patuan.service.Behavior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements Behavior<CategoryReponse, CategoryRequest, Long> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<CategoryReponse> getAllPage(Integer pageNo, Integer size) {
        return categoryRepository.getAllPage(PageRequest.of(pageNo, size));
    }

    @Override
    public List<CategoryReponse> getAll() {
        return categoryRepository.getAll();
    }

    @Override
    public CategoryRequest getOne(Long aLong) {
        return categoryRepository.findOne(aLong);
    }

    @Override
    public CategoryReponse getOneT(Long aLong) {
        return null;
    }

    @Override
    public void saveOrUpdate(CategoryRequest categoryRequest) {
        Category cate = Category.builder()
                .id(categoryRequest.getId())
                .name(categoryRequest.getName())
                .build();
        categoryRepository.save(cate);
    }

    @Override
    public void remove(Long aLong) {
        categoryRepository.deleteById(aLong);
    }
}
