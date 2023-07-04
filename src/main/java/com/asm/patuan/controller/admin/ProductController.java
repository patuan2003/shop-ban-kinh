package com.asm.patuan.controller.admin;


import com.asm.patuan.request.BrandRequest;
import com.asm.patuan.request.CategoryRequest;
import com.asm.patuan.request.ColorRequest;
import com.asm.patuan.request.ManufacturerRequest;
import com.asm.patuan.request.MaterialRequest;
import com.asm.patuan.request.ProductRequest;
import com.asm.patuan.response.BrandReponse;
import com.asm.patuan.response.CategoryReponse;
import com.asm.patuan.response.ColorResponse;
import com.asm.patuan.response.ManufacturerReponse;
import com.asm.patuan.response.MaterialReponse;
import com.asm.patuan.response.ProductResponse;
import com.asm.patuan.service.Behavior;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    private Behavior<ProductResponse, ProductRequest, Long> productService;

    @Autowired
    private Behavior<CategoryReponse, CategoryRequest, Long> categoryService;

    @Autowired
    private Behavior<ColorResponse, ColorRequest, Long> colorService;

    @Autowired
    private Behavior<ManufacturerReponse, ManufacturerRequest, Long> manuService;

    @Autowired
    private Behavior<MaterialReponse, MaterialRequest, Long> materialService;

    @Autowired
    private Behavior<BrandReponse, BrandRequest, Long> brandService;


    @ModelAttribute("product")
    public ProductRequest request() {
        return new ProductRequest();
    }

    @ModelAttribute("listBrand")
    public List<BrandReponse> brandReponse() {
        return brandService.getAll();
    }

    @ModelAttribute("listColor")
    public List<ColorResponse> colorResponse() {
        return colorService.getAll();
    }

    @ModelAttribute("listCate")
    public List<CategoryReponse> categoryReponses() {
        return categoryService.getAll();
    }

    @ModelAttribute("listManu")
    public List<ManufacturerReponse> manuRequest() {
        return manuService.getAll();
    }

    @ModelAttribute("listMaterial")
    public List<MaterialReponse> materialReponses() {
        return materialService.getAll();
    }

    @GetMapping("")
    public String getAll(@RequestParam(name = "page", defaultValue = "0") Integer pageNo, Model model) {
//        model.addAttribute("listBrand", brandService.getAll());
//        model.addAttribute("listCate", categoryService.getAll());
//        model.addAttribute("listColor", colorService.getAll());
//        model.addAttribute("listManu", manuService.getAll());
//        model.addAttribute("listMaterial", materialService.getAll());
        model.addAttribute("listProduct", productService.getAllPage(pageNo, 5));
        return "admin/product";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("id") Long id,
                         Model model,
                         @RequestParam(name = "page", defaultValue = "0") Integer pageNo) {
        model.addAttribute("listProduct", productService.getAllPage(pageNo, 5));
        model.addAttribute("product", productService.getOne(id));
        return "admin/product";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam("id") Long id, Model model) {
        productService.remove(id);
        return "redirect:/admin/product";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("product") ProductRequest request, BindingResult result,
                      @RequestParam("img") MultipartFile multipartFile,
                      @RequestParam(name = "page", defaultValue = "0") Integer pageNo,
                      Model model) {
        model.addAttribute("listProduct", productService.getAllPage(pageNo, 5));
        if (result.hasErrors()) {
            return "admin/product";
        } else {
            String img = multipartFile.getOriginalFilename();
            String path = "/images/" + img;
            try {
                request.setImg(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
            productService.saveOrUpdate(request);
            return "redirect:/admin/product";
        }
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("product") ProductRequest request,
                         @RequestParam("img") MultipartFile multipartFile
    ) {

        String img = multipartFile.getOriginalFilename();
        String path = "/images/" + img;
        try {
            request.setImg(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        productService.saveOrUpdate(request);
        return "redirect:/admin/product";

    }
}
