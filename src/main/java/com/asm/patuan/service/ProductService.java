package com.asm.patuan.service;

import com.asm.patuan.request.FilterRequest;
import com.asm.patuan.response.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {

    Page<ProductResponse> filter(FilterRequest request, Integer pageNo, Integer size);

}
