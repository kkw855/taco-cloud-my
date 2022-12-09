package tacos.web;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tacos.Ingredient;
import tacos.data.IngredientRepository;

@RequiredArgsConstructor
@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

  private final IngredientRepository ingredientRepo;

  @Override
  public Ingredient convert(String id) {
    final var optionIngredient = ingredientRepo.findById(id);

    // TODO:
    return optionIngredient.isDefined() ?
      optionIngredient.get() : null;
  }
}
