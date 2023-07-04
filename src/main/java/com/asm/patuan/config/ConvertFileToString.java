package com.asm.patuan.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.web.multipart.MultipartFile;

public class ConvertFileToString implements Converter<MultipartFile, String> {
    @Override
    public String convert(MultipartFile source) {
        return source.getOriginalFilename();
    }
}
