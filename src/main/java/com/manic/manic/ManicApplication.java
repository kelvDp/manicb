package com.manic.manic;

import com.manic.manic.data.IngredientRepo;
import com.manic.manic.Ingredient.Type;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ManicApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManicApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(IngredientRepo repo ) {
		return args -> {
			repo.save(new Ingredient("WTRL", "White Roll", Type.ROLL));
			repo.save(new Ingredient("BNRL", "Brown Roll", Type.ROLL));
			repo.save(new Ingredient("HLRL", "Seeded Health Roll", Type.ROLL));
			repo.save(new Ingredient("CHKN", "Chicken", Type.PROTEIN));
			repo.save(new Ingredient("BEEF", "Beef", Type.PROTEIN));
			repo.save(new Ingredient("PORK", "Pulled Pork", Type.PROTEIN));
			repo.save(new Ingredient("TMTO", "Tomato", Type.VEGGIES));
			repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
			repo.save(new Ingredient("MSRM", "Mushrooms", Type.VEGGIES));
			repo.save(new Ingredient("PKLS", "Pickles", Type.VEGGIES));
			repo.save(new Ingredient("CMON", "Caramelized Onion", Type.VEGGIES));
			repo.save(new Ingredient("CHED", "Cheddar Cheese", Type.CHEESE));
			repo.save(new Ingredient("PRSS", "Pepper Sauce", Type.SAUCE));
			repo.save(new Ingredient("MSSS", "Mushroom Sauce", Type.SAUCE));
		};
	}
}
