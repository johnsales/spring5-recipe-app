package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author jmsantiago on 09/04/2020
 */
@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes(){
        log.debug("Loading Boostrap Data");

        List<Recipe> recipes = new ArrayList<>(2);

        //get UOMs
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if(!tableSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if(!teaSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
        if(!dashUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pinch");
        if(!pintUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");
        if(!cupsUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        //get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupsUom = cupsUomOptional.get();

        //get Categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if(!americanCategoryOptional.isPresent())
            throw new RuntimeException("Expected Category Not Found");

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if(!mexicanCategoryOptional.isPresent())
            throw new RuntimeException("Expected Category Not Found");

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        //Yummy Guac
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTimer(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1 Cut avocado \n 2 Mash with a fork \n 3 Add salt \n 4 Cover with plastic \n\n Read more: http://www.simplyrecipes.com");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup \n\n Read more: http://www.simplyrecipes.com");

        guacRecipe.setNotes(guacNotes);

        //very redundent - could add helper method, and make this simpler
        guacRecipe.getIngredients().add(new Ingredient("ripe avocados",new BigDecimal(2),eachUom));
        guacRecipe.getIngredients().add(new Ingredient("Kosher salt",new BigDecimal(".5"),teaSpoonUom));
        guacRecipe.getIngredients().add(new Ingredient("fresh lime juice or lemon juice",new BigDecimal(2),tableSpoonUom));
        guacRecipe.getIngredients().add(new Ingredient("minced red onion or thinly sliced green onion",new BigDecimal(2),tableSpoonUom));
        guacRecipe.getIngredients().add(new Ingredient("serrano chiles, stems and seeds removed, minced",new BigDecimal(2),eachUom));
        guacRecipe.getIngredients().add(new Ingredient("Cilantro",new BigDecimal(2),tableSpoonUom));
        guacRecipe.getIngredients().add(new Ingredient("fresh grated black pepper",new BigDecimal(2),dashUom));
        guacRecipe.getIngredients().add(new Ingredient("ripe tomato, seeds and pulp removed, chopped",new BigDecimal(".5"),eachUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        //add to return list
        recipes.add(guacRecipe);

        //Yummy Tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setCookTimer(9);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);
        tacosRecipe.setDirections("1 Stuff \n 2 Stuf  \n\n Read more: http://www.simplyrecipes.com");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("For a very quick tacos ... \n\n Read more: http://www.simplyrecipes.com");

        tacosRecipe.setNotes(tacoNotes);

        tacosRecipe.getIngredients().add(new Ingredient("Ancho Chili Powder",new BigDecimal(2),tableSpoonUom));
        tacosRecipe.getIngredients().add(new Ingredient("Dried Oregano",new BigDecimal(1),teaSpoonUom));
        tacosRecipe.getIngredients().add(new Ingredient("Dried Cumin",new BigDecimal(1),teaSpoonUom));
        tacosRecipe.getIngredients().add(new Ingredient("Sugar",new BigDecimal(1),teaSpoonUom));
        tacosRecipe.getIngredients().add(new Ingredient("Salt",new BigDecimal(".5"),teaSpoonUom));
        //etc...

        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);

        //add to return list
        recipes.add(tacosRecipe);

        return recipes;
    }
}
