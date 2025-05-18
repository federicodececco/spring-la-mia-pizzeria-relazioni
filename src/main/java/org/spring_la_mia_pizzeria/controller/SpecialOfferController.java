package org.spring_la_mia_pizzeria.controller;

import org.spring_la_mia_pizzeria.repository.PizzaRepository;
import org.spring_la_mia_pizzeria.repository.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.spring_la_mia_pizzeria.model.*;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/offers")
public class SpecialOfferController {

    @Autowired
    private SpecialOfferRepository offerRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("offer", offerRepository.findById(id).get());
        return "offers/show";
    }

    @GetMapping("/{id}/create")
    public String create(Model model, @PathVariable("id") Integer id) {

        SpecialOffer offer = new SpecialOffer();
        offer.setPizza(pizzaRepository.findById(id).get());
        model.addAttribute("offer", offer);
        return "offers/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("offer") SpecialOffer formOffer, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "offers/create";
        }
        offerRepository.save(formOffer);
        return "redirect:/" + formOffer.getPizza().getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("offer", offerRepository.findById(id).get());

        return "offers/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid @ModelAttribute("offer") SpecialOffer formOffer, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "offers/edit";
        }

        offerRepository.save(formOffer);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        Pizza pizza = offerRepository.findById(id).get().getPizza();
        offerRepository.deleteById(id);
        return "redirect:/" + pizza.getId();
    }

}
