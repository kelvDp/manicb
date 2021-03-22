package com.manic.manic.data;

import com.manic.manic.Ingredient;

import org.springframework.data.repository.CrudRepository;

public interface IngredientRepo extends CrudRepository<Ingredient, String> {
}
