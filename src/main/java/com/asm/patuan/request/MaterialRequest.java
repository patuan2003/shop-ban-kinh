package com.asm.patuan.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MaterialRequest {

    private Long id;

    @NotBlank(message = "không được dể trống")
    private String name;

}
