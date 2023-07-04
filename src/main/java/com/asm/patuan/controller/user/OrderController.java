package com.asm.patuan.controller.user;

import com.asm.patuan.config.FormatPrice;
import com.asm.patuan.entity.Order;
import com.asm.patuan.entity.OrderDetail;
import com.asm.patuan.request.CartDetailRequest;
import com.asm.patuan.request.ProductRequest;
import com.asm.patuan.response.CartDetailResponse;
import com.asm.patuan.response.ProductResponse;
import com.asm.patuan.service.Behavior;
import com.asm.patuan.service.CartDetailService;
import com.asm.patuan.service.CartService;
import com.asm.patuan.service.CustomerService;
import com.asm.patuan.service.OrderDetailService;
import com.asm.patuan.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    HttpSession session;

    @Autowired
    private CartDetailService cartDetailService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private Behavior<ProductResponse, ProductRequest, Long> productSrevice;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @ModelAttribute("format")
    public FormatPrice format() {
        return new FormatPrice();
    }

    @GetMapping("")
    public String order(Model model) {
        Double totalPrice = 0.0;
        Long cusomerId = (Long) session.getAttribute("id");
        if (cusomerId == null) {
            String userId = session.getAttribute("userId").toString();
            model.addAttribute("listCart", cartDetailService.getCartUser(userId));
            for (CartDetailResponse res : cartDetailService.getCartUser(userId)) {
                totalPrice += Double.valueOf(res.getPrice().stripTrailingZeros().toString()) * res.getQuantity();
            }

            if (cartDetailService.getCartUser(userId) == null) {
                return "redirect:/giong-hang";
            }
        } else {
            model.addAttribute("listCart", cartDetailService.getAllCustomerId(cusomerId));
            for (CartDetailResponse res : cartDetailService.getAllCustomerId(cusomerId)) {
                totalPrice += Double.valueOf(res.getPrice().stripTrailingZeros().toString()) * res.getQuantity();
            }

            if (cartDetailService.getAllCustomerId(cusomerId).isEmpty()) {
                return "redirect:/giong-hang";
            }
        }

        model.addAttribute("totalPrice", totalPrice == null ? 0 : totalPrice);
        return "user/buy-product";
    }

    @PostMapping("/save")
    public String saveOrder(@RequestParam("recipientName") String recipientName,
                            @RequestParam("phoneAddress") String phoneAddress,
                            @RequestParam("recipientName") String shippingAddress
    ) {
        Long cusomerId = (Long) session.getAttribute("id");
        if (cusomerId == null) {
            String userId = session.getAttribute("userId").toString();
            Order order = Order.builder()
                    .recipientName(recipientName)
                    .phoneAddress(phoneAddress)
                    .shippingAddress(shippingAddress)
                    .userId(userId)
                    .orderDate(new Date())
                    .orderStatus(1)
                    .build();
            orderService.save(order);

            List<CartDetailRequest> cartItems = cartDetailService.getCartDetailRequest(userId);
            for (CartDetailRequest req : cartItems) {
                OrderDetail orderDetail = OrderDetail.builder()
                        .product(orderDetailService.getProduct(req.getProductId()))
                        .price(req.getPrice())
                        .quantity(req.getQuantity())
                        .order(order)
                        .build();
                orderDetailService.save(orderDetail);
                orderDetailService.updateQuantity(productSrevice.getOne(req.getProductId()).getQuantity() - req.getQuantity(), req.getProductId());
                cartDetailService.delete(req.getId());
                cartService.delete(req.getCartId());
            }
        } else {
            Order order = Order.builder()
                    .recipientName(recipientName)
                    .phoneAddress(phoneAddress)
                    .shippingAddress(shippingAddress)
                    .customer(customerService.getId(cusomerId).get())
                    .orderDate(new Date())
                    .orderStatus(1)
                    .build();
            orderService.save(order);

            List<CartDetailRequest> cartItems = cartDetailService.getCartDetailCustomer(cusomerId);
            for (CartDetailRequest req : cartItems) {
                OrderDetail orderDetail = OrderDetail.builder()
                        .product(orderDetailService.getProduct(req.getProductId()))
                        .price(req.getPrice())
                        .quantity(req.getQuantity())
                        .order(order)
                        .build();
                orderDetailService.save(orderDetail);
                orderDetailService.updateQuantity(productSrevice.getOne(req.getProductId()).getQuantity() - req.getQuantity(), req.getProductId());
                cartDetailService.delete(req.getId());
                cartService.delete(req.getCartId());
            }
        }
        return "redirect:/user/history";
    }


}
