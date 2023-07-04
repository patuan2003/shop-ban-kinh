package com.asm.patuan.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FilterRequest {

    private Integer page;

    private Long brandId;

    private Long manufacturerId;

    private Long colorId;

    private Long material;

    private Long categoryId;

}
