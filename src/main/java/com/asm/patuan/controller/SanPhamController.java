package com.asm.patuan.controller;

import com.asm.patuan.config.FormatPrice;
import com.asm.patuan.request.BrandRequest;
import com.asm.patuan.request.CartDetailRequest;
import com.asm.patuan.request.CategoryRequest;
import com.asm.patuan.request.ColorRequest;
import com.asm.patuan.request.FilterRequest;
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
import com.asm.patuan.service.CartDetailService;
import com.asm.patuan.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SanPhamController {
    @Autowired
    private Behavior<ProductResponse, ProductRequest, Long> productService;

    @Autowired
    private Behavior<CategoryReponse, CategoryRequest, Long> categoryService;

    @Autowired
    private Behavior<BrandReponse, BrandRequest, Long> brandService;

    @Autowired
    private Behavior<MaterialReponse, MaterialRequest, Long> materialService;

    @Autowired
    private Behavior<ManufacturerReponse, ManufacturerRequest, Long> manuService;

    @Autowired
    private Behavior<ColorResponse, ColorRequest, Long> colorService;

    @Autowired
    private ProductService proService;

    @Autowired
    HttpSession session;

    @ModelAttribute("gioHang")
    public CartDetailRequest cartDetailRequest() {
        return new CartDetailRequest();
    }

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

    @ModelAttribute("filter")
    public FilterRequest filterRequest() {
        return new FilterRequest();
    }

    @Autowired
    private FormatPrice formatPrice;

    @Autowired
    private CartDetailService cartDetailService;

    @Autowired
    private Behavior<CategoryReponse, CategoryRequest, Long> cate;

    @GetMapping("/san-pham")
    public String getAll(Model model,
                         @RequestParam(name = "page", defaultValue = "0") Integer pageNo) {
        Long cusomerId = (Long) session.getAttribute("id");
        if (cusomerId == null) {
            model.addAttribute("count", cartDetailService.getCartUser(session.getAttribute("userId").toString()).size());
        } else {
            model.addAttribute("count", cartDetailService.getAllCustomerId(cusomerId).size());
        }
        model.addAttribute("format", formatPrice);
        model.addAttribute("listProduct", productService.getAllPage(pageNo, 12));
        return "san-pham";
    }

    @GetMapping("/san-pham/chi-tiet-san-pham/{id}")
    public String DetailPr(Model model, @PathVariable("id") Long id) {
        Long cusomerId = (Long) session.getAttribute("id");
        if (cusomerId == null) {
            model.addAttribute("count", cartDetailService.getCartUser(session.getAttribute("userId").toString()).size());
        } else {
            model.addAttribute("count", cartDetailService.getAllCustomerId(cusomerId).size());
        }
        model.addAttribute("format", formatPrice);
        model.addAttribute("detail", productService.getOne(id));
        model.addAttribute("cateName", cate.getOne(productService.getOne(id).getCategoryId()).getName());
        return "user/add-cart";
    }

    @GetMapping("/san-pham/filter")
    public String filter(@ModelAttribute("filter") FilterRequest filter,
                         @RequestParam(name = "page", defaultValue = "0") Integer pageNo,
                         Model model) {
        Long cusomerId = (Long) session.getAttribute("id");
        if (cusomerId == null) {
            model.addAttribute("count", cartDetailService.getCartUser(session.getAttribute("userId").toString()).size());
        } else {
            model.addAttribute("count", cartDetailService.getAllCustomerId(cusomerId).size());
        }
        model.addAttribute("isFilter", true);
        model.addAttribute("cate", filter.getCategoryId());
        model.addAttribute("color", filter.getColorId());
        model.addAttribute("brand", filter.getBrandId());
        model.addAttribute("mar", filter.getMaterial());
        model.addAttribute("manu", filter.getManufacturerId());
        model.addAttribute("listProduct", proService.filter(filter, pageNo, 12));
        return "san-pham";
    }
}
