package org.spring_la_mia_pizzeria.controller;

import org.spring_la_mia_pizzeria.model.*;
import org.spring_la_mia_pizzeria.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository repository;

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("order") Order formOrder, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "orders/create";
        }
        repository.save(formOrder);
        return "redirect:/pizzas/" + formOrder.getPizza().getId();
    }
}
