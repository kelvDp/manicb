package com.manic.manic.web;

import com.manic.manic.Burger;
import com.manic.manic.User;
import com.manic.manic.Ingredient;
import com.manic.manic.Order;
import com.manic.manic.data.BurgerRepo;
import com.manic.manic.data.UserRepo;
import com.manic.manic.data.IngredientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/burgers")
@SessionAttributes("order")
public class BurgerController {

    private final IngredientRepo ingredientRepo;
    private final BurgerRepo burgerRepo;
    private final UserRepo userRepo;

    @Autowired
    BurgerController(IngredientRepo ingredientRepo, BurgerRepo burgerRepo, UserRepo userRepo) {
        this.ingredientRepo = ingredientRepo;
        this.burgerRepo = burgerRepo;
        this.userRepo = userRepo;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "burger")
    public Burger burger() {
        return new Burger();
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type types) {
        return ingredients.stream().filter(x -> x.getType().equals(types)).collect(Collectors.toList());
    }

    @GetMapping
    public String createBurger(Model model, @AuthenticationPrincipal User user) {

        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(ingredients::add);

        Ingredient.Type[] types = Ingredient.Type.values();

        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        User currentUser = userRepo.findByUsername(user.getUsername());

        model.addAttribute("user", currentUser);

        return "buildburger";
    }

    @PostMapping
    public String processOrder(Burger burger, Errors errors, @ModelAttribute Order order) {

        if (errors.hasErrors()) {
            return "orderForm";
        }

        Burger savedItem = burgerRepo.save(burger);

        order.addBurger(savedItem);

        return "redirect:/orders/current";
    }
}
