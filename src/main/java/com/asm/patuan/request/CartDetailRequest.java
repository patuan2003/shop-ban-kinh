package com.asm.patuan.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDetailRequest {

    private Long id;

    private Long cartId;

    private Long productId;

    private Integer quantity;

    private BigDecimal price;
}
