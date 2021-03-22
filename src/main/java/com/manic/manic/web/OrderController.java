package com.manic.manic.web;

import com.manic.manic.User;
import com.manic.manic.Order;
import com.manic.manic.data.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private final OrderRepo orderRepo;

    @Autowired
    OrderController(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @GetMapping("/current")
    public String showOrder(@AuthenticationPrincipal User user, @ModelAttribute Order order) {

        if (order.getPersonName() == null) {
            order.setPersonName(user.getUsername());
        }

        if (order.getStreet() == null) {
            order.setStreet(user.getStreet());
        }

        if (order.getCity() == null) {
            order.setCity(user.getCity());
        }

        if (order.getState() == null) {
            order.setState(user.getState());
        }

        if (order.getZip() == null) {
            order.setZip(user.getZip());
        }

        return "orderForm";
    }

    @PostMapping
    public String processOrder(Order order, Errors errors, SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {

        if (errors.hasErrors()) {
            return "orderForm";
        }

        order.setUser(user);

        orderRepo.save(order);

        sessionStatus.setComplete();

        return "success";
    }
}
