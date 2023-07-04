package com.asm.patuan.controller.user;

import com.asm.patuan.config.FormatPrice;
import com.asm.patuan.entity.Cart;
import com.asm.patuan.request.CartDetailRequest;
import com.asm.patuan.response.CartDetailResponse;
import com.asm.patuan.service.CartDetailService;
import com.asm.patuan.service.CartService;
import com.asm.patuan.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;


@Controller
public class CartController {


    @Autowired
    private CartService cartService;

    @Autowired
    private CartDetailService cartDetailService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private HttpSession session;

    @Autowired
    private FormatPrice formatPrice;

    @ModelAttribute("name")
    public String name() {
        return session.getAttribute("name") == null ? "" : session.getAttribute("name").toString();
    }

    @GetMapping("/gio-hang")
    public String gioHang(Model model) {
        Long cusomerId = (Long) session.getAttribute("id");
        BigDecimal totalPrice = BigDecimal.ZERO;
        if (cusomerId == null) {
            String userId = session.getAttribute("userId").toString();
            model.addAttribute("count", cartDetailService.getCartUser(userId).size());
            model.addAttribute("listCart", cartDetailService.getCartUser(userId));
            for (CartDetailResponse res : cartDetailService.getCartUser(userId)) {
                totalPrice = totalPrice.add(res.getPrice().multiply(new BigDecimal(res.getQuantity())));
            }
        } else {
            model.addAttribute("count", cartDetailService.getCartDetailCustomer(cusomerId).size());
            model.addAttribute("listCart", cartDetailService.getAllCustomerId(cusomerId));
            for (CartDetailResponse res : cartDetailService.getAllCustomerId(cusomerId)) {
                totalPrice = totalPrice.add(res.getPrice().multiply(new BigDecimal(res.getQuantity())));
            }
        }
        model.addAttribute("format", formatPrice);
        model.addAttribute("totalPrice", totalPrice == null ? 0 : formatPrice.formatPrice(totalPrice));
        return "user/cart";
    }


    @PostMapping("/add-cart")
    public String gioHang(
            @RequestParam("productId") Long id,
            @RequestParam("price") BigDecimal price,
            @RequestParam("quantity") Integer quantity,
            Model model, RedirectAttributes ra
    ) {
        Long cusomerId = (Long) session.getAttribute("id");

        if (quantity > cartDetailService.getProduct(id).getQuantity()) {
            ra.addFlashAttribute("isQuantity", true);
            return "redirect:/san-pham/chi-tiet-san-pham/" + id;
        }
        if (cusomerId == null) {
            String userId = session.getAttribute("userId").toString();
            model.addAttribute("count", cartDetailService.getCartUser(userId).size());
            Cart cart = new Cart();
            CartDetailRequest cartDetail = new CartDetailRequest();
            cart.setUserCartId(userId);
            if (cartDetailService.findByUserAndProduct(userId, id) != null) {
                cartDetail.setId(cartDetailService.findByUserAndProduct(userId, id).getId());
                cartDetail.setProductId(id);
                cartDetail.setCartId((cartDetailService.findByUserAndProduct(userId, id).getCartId()));
                cartDetail.setPrice(price);
                cartDetail.setQuantity(cartDetailService.findByUserAndProduct(userId, id).getQuantity() + quantity);
                cartDetailService.save(cartDetail);
            } else {
                cartDetail.setPrice(price);
                cartDetail.setProductId(id);
                cartDetail.setQuantity(quantity);
                cartService.save(cart);
                cartDetail.setCartId(cart.getId());
                cartDetailService.save(cartDetail);
            }
        } else {
            model.addAttribute("count", cartDetailService.getAllCustomerId(cusomerId).size());
            Cart cart = new Cart();
            CartDetailRequest cartDetail = new CartDetailRequest();
            cart.setCustomer(customerService.getId(cusomerId).get());
            if (cartDetailService.findByCustomerAndProduct(cusomerId, id) != null) {
                cartDetail.setId(cartDetailService.findByCustomerAndProduct(cusomerId, id).getId());
                cartDetail.setProductId(id);
                cartDetail.setCartId((cartDetailService.findByCustomerAndProduct(cusomerId, id).getCartId()));
                cartDetail.setPrice(price);
                cartDetail.setQuantity(cartDetailService.findByCustomerAndProduct(cusomerId, id).getQuantity() + quantity);
                cartDetailService.save(cartDetail);
            } else {
                cartDetail.setPrice(price);
                cartDetail.setProductId(id);
                cartDetail.setQuantity(quantity);
                cartService.save(cart);
                cartDetail.setCartId(cart.getId());
                cartDetailService.save(cartDetail);
            }
        }
        return "redirect:/gio-hang";
    }


    @GetMapping("/cart-detail/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        Long cusomerId = (Long) session.getAttribute("id");

        if (cusomerId == null) {
            model.addAttribute("count", cartDetailService.getCartUser(session.getAttribute("userId").toString()).size());
            CartDetailRequest cartDetail = cartDetailService.getCartDetail(id);
            Long cartId = cartDetail.getCartId();
            cartDetailService.delete(id);
            cartService.delete(cartId);
        } else {
            model.addAttribute("count", cartDetailService.getAllCustomerId(cusomerId).size());
            CartDetailRequest cartDetail = cartDetailService.getCartDetail(id);
            Long cartId = cartDetail.getCartId();
            cartDetailService.delete(id);
            cartService.delete(cartId);
        }
        return "redirect:/gio-hang";
    }

}
