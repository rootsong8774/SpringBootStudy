package com.tacojdbctemplate.controller;

import com.tacojdbctemplate.domain.TacoOrder;
import com.tacojdbctemplate.repository.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private OrderRepository orderRepository;

    public OrderRepository getOrderRepository() {
        return orderRepository;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(TacoOrder order, Errors error) {
        if (error.hasErrors()) {
            return "orderForm";
        }
        orderRepository.save(order);
        return "redirect:/";
    }
}
