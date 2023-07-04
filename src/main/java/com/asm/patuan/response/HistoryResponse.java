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
public class HistoryResponse {

    private Long id;

    private String name;

    private String image;

    private Integer status;

    private Integer quantity;

    private BigDecimal price;

}
