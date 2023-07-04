package com.asm.patuan.controller.admin;


import com.asm.patuan.request.ManufacturerRequest;
import com.asm.patuan.response.ManufacturerReponse;
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
@RequestMapping("/admin/manufacturer")
public class ManufacturerController {

    @Autowired
    private Behavior<ManufacturerReponse, ManufacturerRequest, Long> manufactuerService;


    @ModelAttribute("manufacturer")
    public ManufacturerRequest request() {
        return new ManufacturerRequest();
    }


    @GetMapping("")
    public String getAll(
            @RequestParam(name = "page", defaultValue = "0") Integer pageNo, Model model
    ) {
        model.addAttribute("listManu", manufactuerService.getAllPage(pageNo, 5));
        return "admin/manufacturer";
    }

    @GetMapping("/detail")
    public String getAll(@RequestParam("id") Long id,
                         @RequestParam(name = "page", defaultValue = "0") Integer pageNo,
                         Model model) {
        model.addAttribute("listManu", manufactuerService.getAllPage(pageNo, 5));
        model.addAttribute("manufacturer", manufactuerService.getOne(id));
        return "admin/manufacturer";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam("id") Long id) {
        manufactuerService.remove(id);
        return "redirect:/admin/manufacturer";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("manufacturer") ManufacturerRequest request,
                      BindingResult result, Model model, @RequestParam(name = "page", defaultValue = "0") Integer pageNo) {
        model.addAttribute("listManu", manufactuerService.getAllPage(pageNo, 5));
        if (result.hasErrors()) {
            return "/admin/manufacturer";
        } else {
            manufactuerService.saveOrUpdate(request);
            return "redirect:/admin/manufacturer";
        }
    }

    @PostMapping("/update")
    public String add(@ModelAttribute("manufacturer") ManufacturerRequest request) {
        manufactuerService.saveOrUpdate(request);
        return "redirect:/admin/manufacturer";
    }


}
