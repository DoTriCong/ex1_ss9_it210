package com.restaurant.ex1.controller;

import com.restaurant.ex1.dto.ProductDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam("id") String id,
                            @RequestParam("name") String name,
                            @RequestParam("price") double price,
                            HttpSession session) {

        List<ProductDTO> cart = (List<ProductDTO>) session.getAttribute("myCart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        cart.add(new ProductDTO(id, name, price));
        session.setAttribute("myCart", cart);

        return "redirect:/checkout";
    }

        @GetMapping("/checkout")
    public String viewCheckout(HttpSession session, Model model) {
        List<ProductDTO> cart = (List<ProductDTO>) session.getAttribute("myCart");

        if (cart == null || cart.isEmpty()) {
            model.addAttribute("message", "Giỏ hàng của bạn đang trống!");
        } else {
            model.addAttribute("message", "Bạn có " + cart.size() + " sản phẩm trong giỏ.");
            model.addAttribute("cart", cart);
        }
        return "checkout-page";
    }
}
