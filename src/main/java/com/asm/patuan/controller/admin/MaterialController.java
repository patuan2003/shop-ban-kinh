package com.asm.patuan.controller.admin;

import com.asm.patuan.request.MaterialRequest;
import com.asm.patuan.response.MaterialReponse;
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
@RequestMapping("/admin/material")
public class MaterialController {

    @Autowired
    private Behavior<MaterialReponse, MaterialRequest, Long> materialService;

    @ModelAttribute("material")
    public MaterialRequest materialRequest() {
        return new MaterialRequest();
    }

    private Long idUpdate = null;

    @GetMapping("")
    public String getAll(@RequestParam(name = "page", defaultValue = "0") Integer pageNo, Model model) {
        model.addAttribute("listMaterial", materialService.getAllPage(pageNo, 5));
        return "admin/material";
    }

    @GetMapping("/detail")
    public String detail(Model model, @RequestParam("id") Long id,
                         @RequestParam(name = "page", defaultValue = "0") Integer pageNo) {
        model.addAttribute("listMaterial", materialService.getAllPage(pageNo, 5));
        model.addAttribute("material", materialService.getOne(id));
        return "admin/material";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam("id") Long id) {
        materialService.remove(id);
        return "redirect:/admin/material";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("material") MaterialRequest request,
                      BindingResult result,
                      Model model, @RequestParam(name = "page", defaultValue = "0") Integer pageNo) {
        model.addAttribute("listMaterial", materialService.getAllPage(pageNo, 5));
        if (result.hasErrors()) {
            return "/admin/material";
        } else {
            materialService.saveOrUpdate(request);
            return "redirect:/admin/material";
        }
    }

    @PostMapping("/update")
    public String add(@ModelAttribute("material") MaterialRequest request) {
        materialService.saveOrUpdate(request);
        return "redirect:/admin/material";

    }
}
