package com.asm.patuan.controller.admin;

import com.asm.patuan.response.ThongKeReponse;
import com.asm.patuan.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ThongKeController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/admin/thong-ke")
    public String thongKe(Model model) {
        List<String> lable = new ArrayList<>();
        List<Long> value = new ArrayList<>();
        for (ThongKeReponse res :
                orderDetailService.thongKe()) {
            lable.add(res.getName());
            value.add(res.getTotalQuantity());
        }
        model.addAttribute("listTK",orderDetailService.thongKe());
        model.addAttribute("label", lable);
        model.addAttribute("value", value);
        return "admin/ThongKe";
    }

}
