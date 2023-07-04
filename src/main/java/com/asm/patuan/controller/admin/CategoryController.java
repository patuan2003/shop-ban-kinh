package com.asm.patuan.controller.admin;

import com.asm.patuan.request.CategoryRequest;
import com.asm.patuan.response.CategoryReponse;
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

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private Behavior<CategoryReponse, CategoryRequest, Long> categoryService;


    @ModelAttribute("category")
    public CategoryRequest category() {
        return new CategoryRequest();
    }


    @GetMapping("")
    public String getAll(@RequestParam(name = "page", defaultValue = "0") Integer pageNo, Model model) {
        model.addAttribute("listCate", categoryService.getAllPage(pageNo, 5));
        return "admin/category";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("category") CategoryRequest request,
                      BindingResult result,
                      @RequestParam(name = "page", defaultValue = "0") Integer pageNo, Model model) {
        model.addAttribute("listCate", categoryService.getAllPage(pageNo, 5));
        if (result.hasErrors()) {
            return "admin/category";
        } else {
            categoryService.saveOrUpdate(request);
            return "redirect:/admin/category";
        }
    }

    @GetMapping("/remove")
    public String remvoe(@RequestParam("id") Long id) {
        categoryService.remove(id);
        return "redirect:/admin/category";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("id") Long id, Model model,
                         @RequestParam(name = "page", defaultValue = "0") Integer pageNo) {
        model.addAttribute("listCate", categoryService.getAllPage(pageNo, 5));
        model.addAttribute("category", categoryService.getOne(id));
        return "admin/category";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("category") CategoryRequest request) {
        categoryService.saveOrUpdate(request);
        return "redirect:/admin/category";
    }

}
