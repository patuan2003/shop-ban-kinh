package com.asm.patuan.service.impl;

import com.asm.patuan.entity.Product;
import com.asm.patuan.repository.BrandRepository;
import com.asm.patuan.repository.CategoryRepository;
import com.asm.patuan.repository.ColorRepository;
import com.asm.patuan.repository.ManufacturerRepository;
import com.asm.patuan.repository.MaterialRepository;
import com.asm.patuan.repository.ProductRepository;
import com.asm.patuan.request.FilterRequest;
import com.asm.patuan.request.ProductRequest;
import com.asm.patuan.response.ProductResponse;
import com.asm.patuan.service.Behavior;
import com.asm.patuan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Service
public class ProductServiceImpl implements Behavior<ProductResponse, ProductRequest, Long>, ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<ProductResponse> getAllPage(Integer pageNo, Integer size) {
        return productRepository.getAllPage(PageRequest.of(pageNo, size));
    }

    @Override
    public List<ProductResponse> getAll() {
        return productRepository.getAll();
    }

    @Override
    public ProductRequest getOne(Long aLong) {
        return productRepository.findOne(aLong);
    }

    @Override
    public ProductResponse getOneT(Long aLong) {
        return null;
    }

    @Override
    public void saveOrUpdate(ProductRequest request) {
        Product product = Product.builder()
                .id(request.getId())
                .code(request.getCode().toUpperCase())
                .name(request.getName())
                .price(request.getPrice())
                .image(request.getImg())
                .quantity(request.getQuantity())
                .description(request.getDescription())
                .brand(brandRepository.findById(request.getBrandId()).get())
                .color(colorRepository.findById(request.getColorId()).get())
                .manufacturer(manufacturerRepository.findById(request.getManufacturerId()).get())
                .material(materialRepository.findById(request.getMaterialId()).orElse(null))
                .category(categoryRepository.findById(request.getCategoryId()).get())
                .build();
        productRepository.save(product);
    }

    @Override
    public void remove(Long aLong) {
        productRepository.deleteById(aLong);
    }

    @Override
    public Page<ProductResponse> filter(FilterRequest request, Integer pageNo, Integer size) {
        return productRepository.filterProduct(request.getCategoryId(), request.getColorId(), request.getMaterial(),
                request.getManufacturerId(), request.getBrandId(), PageRequest.of(pageNo, size));
    }
}
