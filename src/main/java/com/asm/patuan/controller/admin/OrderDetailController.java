package com.asm.patuan.controller.admin;

import com.asm.patuan.config.FormatPrice;
import com.asm.patuan.service.OrderDetailService;
import com.asm.patuan.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/order")
public class OrderDetailController {

    @Autowired
    private OrderDetailService service;

    @Autowired
    private OrderService orderService;

    @Autowired
    private FormatPrice formatPrice;
    @GetMapping("")
    public String order(Model model,
                        @RequestParam(name = "page", defaultValue = "0") Integer pageNo) {
        model.addAttribute("format", formatPrice);
        model.addAttribute("listAllOrder", service.getAllOrder(pageNo, 10));
        return "admin/order";
    }

    @GetMapping("/confirm/{id}")
    public String update(@PathVariable("id") Long id) {
        orderService.updateStatusConfirm(id);
        return "redirect:/admin/order";
    }

}
