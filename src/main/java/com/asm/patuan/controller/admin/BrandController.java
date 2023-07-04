package com.asm.patuan.controller.admin;

import com.asm.patuan.request.BrandRequest;
import com.asm.patuan.response.BrandReponse;
import com.asm.patuan.service.Behavior;
import jakarta.servlet.http.HttpSession;
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

@Controller
@RequestMapping("/admin/brand")
public class BrandController {

    @Autowired
    private Behavior<BrandReponse, BrandRequest, Long> brandService;

    @Autowired
    HttpSession session;

    private Long idUpdate = null;

    @ModelAttribute("brand")
    public BrandRequest brandRequest() {
        return new BrandRequest();
    }


    @GetMapping("")
    public String getAll(@RequestParam(name = "page", defaultValue = "0") Integer pageNo, Model model) {
        model.addAttribute("listBrand", brandService.getAllPage(pageNo, 5));
        return "admin/brand";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("id") Long id, @RequestParam(name = "page", defaultValue = "0") Integer pageNo, Model model) {
        model.addAttribute("listBrand", brandService.getAllPage(pageNo, 5));
        model.addAttribute("brand", brandService.getOne(id));
        return "admin/brand";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam("id") Long id) {
        brandService.remove(id);
        return "redirect:/admin/brand";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("brand") BrandRequest request,
                      BindingResult result,
                      @RequestParam(name = "page", defaultValue = "0") Integer pageNo,
                      Model model) {
        model.addAttribute("listBrand", brandService.getAllPage(pageNo, 5));
        if (result.hasErrors()) {
            return "admin/brand";
        } else {
            brandService.saveOrUpdate(request);
            return "redirect:/admin/brand";
        }
    }

    @PostMapping("/update")
    public String add(@ModelAttribute("brand") BrandRequest request) {
        request.setId(idUpdate);
        brandService.saveOrUpdate(request);
        return "redirect:/admin/brand";
    }

}
