package com.asm.patuan.controller.admin;

import com.asm.patuan.request.ColorRequest;
import com.asm.patuan.response.ColorResponse;
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
@RequestMapping("/admin/color")
public class ColorController {

    @Autowired
    private Behavior<ColorResponse, ColorRequest, Long> colorService;

    @ModelAttribute("color")
    public ColorRequest colorRequest() {
        return new ColorRequest();
    }


    @GetMapping("")
    public String getAll(Model model, @RequestParam(name = "page", defaultValue = "0") Integer pageNo) {
        model.addAttribute("listColor", colorService.getAllPage(pageNo, 5));
        return "admin/color";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("color") ColorRequest request,
                      BindingResult result,
                      @RequestParam(name = "page", defaultValue = "0") Integer pageNo, Model model) {
        model.addAttribute("listColor", colorService.getAllPage(pageNo, 5));
        if (result.hasErrors()) {
            return "admin/color";
        } else {
            colorService.saveOrUpdate(request);
            return "redirect:/admin/color";
        }
    }

    @GetMapping("/remove")
    public String remove(@RequestParam("id") Long id) {
        colorService.remove(id);
        return "redirect:/admin/color";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("id") Long id,
                         @RequestParam(name = "page", defaultValue = "0") Integer pageNo, Model model) {
        model.addAttribute("listColor", colorService.getAllPage(pageNo, 5));

        model.addAttribute("color", colorService.getOne(id));
        return "admin/color";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("color") ColorRequest request) {
        colorService.saveOrUpdate(request);
        return "redirect:/admin/color";
    }

}
