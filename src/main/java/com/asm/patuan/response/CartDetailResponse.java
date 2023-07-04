package com.asm.patuan.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDetailResponse {

    private Long id;

    private Long productId;

    private String image;

    private String cateName;

    private String productName;

    private Integer quantity;

    private BigDecimal price;

    private Long cartId;

}
