package com.asm.patuan.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CancelOrderRequest {

    private Long orderDetailId;

    private Long orderId;

    private Long productId;

    private Integer productQuantity;

    private Integer orderDetailQuantity;

}
