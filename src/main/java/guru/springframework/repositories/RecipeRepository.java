package guru.springframework.repositories;

import guru.springframework.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * @author jmsantiago on 09/04/2020
 */
public interface RecipeRepository extends CrudRepository<Recipe,Long> {
}
