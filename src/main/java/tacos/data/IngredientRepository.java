package tacos.data;

import io.vavr.collection.List;
import io.vavr.control.Option;
import org.springframework.data.repository.Repository;
import tacos.Ingredient;

public interface IngredientRepository extends Repository<Ingredient, String> {
  List<Ingredient> findAll();
  Option<Ingredient> findById(String id);
  Ingredient save(Ingredient ingredient);
}
