package guru.springframework.services;

import guru.springframework.domain.Recipe;

import java.util.Set;

/**
 * @author jmsantiago on 09/04/2020
 */
public interface RecipeService {
    Set<Recipe> getRecipes();
}
