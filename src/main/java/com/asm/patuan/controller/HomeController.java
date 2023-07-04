package com.asm.patuan.controller;

import com.asm.patuan.config.FormatPrice;
import com.asm.patuan.request.ProductRequest;
import com.asm.patuan.response.ProductResponse;
import com.asm.patuan.service.Behavior;
import com.asm.patuan.service.CartDetailService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/kinh-mat")
public class HomeController {

    @Autowired
    private Behavior<ProductResponse, ProductRequest, Long> productService;

    @Autowired
    private CartDetailService cartDetailService;

    @Autowired
    private HttpSession session;
    //
    @Autowired
    private FormatPrice formatPrice;

    @ModelAttribute("haha")
    public String name() {
        return session.getAttribute("name") == null ? "" : session.getAttribute("name").toString();
    }

    @GetMapping("")
    public String getAll(Model model,
                         HttpServletRequest request,
                         HttpServletResponse response) {

        Long cusomerId = (Long) session.getAttribute("id");

        if (cusomerId == null) {
            Cookie[] cookies = request.getCookies();
            String sessionId = null;
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("sessionId")) {
                        sessionId = cookie.getValue();
                        break;
                    }
                }
            }

            if (sessionId == null) {
                sessionId = UUID.randomUUID().toString();
                Cookie cookie = new Cookie("sessionId", sessionId);
                cookie.setMaxAge(31536000);
                response.addCookie(cookie);
            }

            session.setAttribute("userId", sessionId);
            model.addAttribute("count", cartDetailService.getCartUser(sessionId).size());
        } else {
            model.addAttribute("count", cartDetailService.getAllCustomerId(cusomerId).size());
        }
        model.addAttribute("format", formatPrice);
        model.addAttribute("listProduct", productService.getAll());
        return "trang-chu";
    }

}
