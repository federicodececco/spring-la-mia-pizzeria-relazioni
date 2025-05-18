package org.spring_la_mia_pizzeria.controller;

import java.util.List;

import org.spring_la_mia_pizzeria.model.Ingredient;
import org.spring_la_mia_pizzeria.repository.IngredientsRepository;
import org.spring_la_mia_pizzeria.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import org.spring_la_mia_pizzeria.model.*;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    IngredientsRepository ingredientsRepository;

    @GetMapping("/")
    public String idnex(Model model) {

        List<Ingredient> ingredients = ingredientsRepository.findAll();
        model.addAttribute("ingredients", ingredients);
        return "ingredients/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "ingredients/create-edit";
    }

    @PostMapping("/create")
    public String persist(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ingredients/create-edit";
        }

        ingredientsRepository.save(formIngredient);
        return "redirect:/ingredients/";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("ingredient", ingredientsRepository.findById(id).get());
        model.addAttribute("edit", true);

        return "ingredients/create-edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ingredients/create-edit";
        }
        ingredientsRepository.save(formIngredient);
        return "redirect:/ingredients/";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        Ingredient remove = ingredientsRepository.findById(id).get();
        for (Pizza linkPizza : remove.getPizzas()) {
            linkPizza.getIngredients().remove(remove);
        }
        ingredientsRepository.delete(remove);
        return "redirect:/ingredients/";
    }

}
