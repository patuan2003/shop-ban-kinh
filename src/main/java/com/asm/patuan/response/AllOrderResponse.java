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
public class AllOrderResponse {

    private Long orderDetailId;

    private Long orderId;

    private Long customerId;

    private String userId;

    private String productName;

    private String image;

    private Integer status;

    private Integer quantity;

    private BigDecimal price;

    private String name;

}
