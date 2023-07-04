package com.asm.patuan.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductRequest {

    private Long id;

    @NotBlank(message = "không được để trống")
    private String code;

    @NotBlank(message = "không được để trống")
    private String name;

    @NotNull(message = "không được để trống")
    private BigDecimal price;

    private String img;

    @NotNull(message = "không được để trống")
    private Integer quantity;

    @NotBlank(message = "không được để trống")
    private String description;

    private Long brandId;

    private Long manufacturerId;

    private Long colorId;

    private Long materialId;

    private Long categoryId;

}
