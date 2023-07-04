package com.asm.patuan.controller.user;

import com.asm.patuan.config.FormatPrice;
import com.asm.patuan.request.CancelOrderRequest;
import com.asm.patuan.response.HistoryResponse;
import com.asm.patuan.service.OrderDetailService;
import com.asm.patuan.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HistoryController {

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private OrderService orderService;

    @Autowired
    HttpSession session;

    @Autowired
    private FormatPrice formatPrice;

    @GetMapping("/user/history")
    public String history(Model model) {
        Double totalPrice = 0.0;
        Long customerId = (Long) session.getAttribute("id");
        if (customerId == null) {
            model.addAttribute("history", orderDetailService.getHistory(session.getAttribute("userId").toString()));
            for (HistoryResponse req : orderDetailService.getHistory(session.getAttribute("userId").toString())) {
                if (req.getStatus() == 1 || req.getStatus() == 2) {
                    totalPrice += Double.valueOf(req.getPrice().stripTrailingZeros().toString()) * req.getQuantity();
                }
            }
        } else {
            model.addAttribute("history", orderDetailService.getHistoryCustomer(customerId));
            if (!orderDetailService.getHistory(session.getAttribute("userId").toString()).isEmpty()) {
                for (HistoryResponse req : orderDetailService.getHistoryCustomer(customerId)) {
                    if (req.getStatus() == 1 || req.getStatus() == 2) {
                        totalPrice += Double.valueOf(req.getPrice().stripTrailingZeros().toString()) * req.getQuantity();
                    }
                }
            }
        }

        model.addAttribute("format", formatPrice);
        model.addAttribute("a", totalPrice);
        return "user/history";
    }

    @GetMapping("/user/cancel/{id}")
    public String cancelOder(@PathVariable("id") Long id) {
        for (CancelOrderRequest request :
                orderDetailService.getCancelOrder(id)) {
            if (orderDetailService.getOne(request.getOrderDetailId()).getId() == request.getOrderDetailId()) {
                orderDetailService.updateQuantity(request.getProductQuantity() + request.getOrderDetailQuantity(), request.getProductId());
            }
        }
        orderDetailService.updateOrderStatus(id);
        return "redirect:/user/history";
    }

    @GetMapping("/user/confirm/{id}")
    public String confirm(@PathVariable("id") Long id) {
        orderDetailService.updateStatusDaNhan(id);
        return "redirect:/user/history";
    }
}
