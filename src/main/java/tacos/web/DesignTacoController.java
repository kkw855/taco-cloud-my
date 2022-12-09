package tacos.web;

import io.vavr.Tuple;
import io.vavr.collection.Array;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import tacos.Ingredient;
import tacos.Order;
import tacos.Taco;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;

@SessionAttributes("order")
@RequiredArgsConstructor
@RequestMapping("/design")
@Controller
public class DesignTacoController {
  private final IngredientRepository ingredientRepo;
  private final TacoRepository tacoRepo;

  @GetMapping
  public String showDesignForm(Model model) {
    final var ingredientList = ingredientRepo.findAll();

    final var types = Array.of(Ingredient.Type.values());

    // TODO: 유닛테스트
    final var ingredientMap =
      types
        .map(type -> Tuple.of(
          type.toString().toLowerCase(),
          ingredientList.filter(x -> x.getType().equals(type)))
        )
        .toMap(pair -> pair._1, pair -> pair._2);

    // TODO: Model 은 mutable 이다
    model.addAllAttributes(ingredientMap.toJavaMap());
    model.addAttribute("taco", new Taco());

    return "design";
  }

  @PostMapping
  public String processDesign(@Valid Taco design, Errors errors, Order order) {
    if (errors.hasErrors()) {
      // TODO: @GetMapping 에서와 똑같이 Ingredient 를 뷰로 전달해야함! 중복 코드 발생!
      return "design";
    }

    final var taco = tacoRepo.save(design);
    order.addDesign(taco);

    return "redirect:/orders/current";
  }
}
