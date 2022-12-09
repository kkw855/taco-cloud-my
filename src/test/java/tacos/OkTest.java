package tacos;

import static org.assertj.core.api.Assertions.assertThat;

import io.vavr.collection.Array;
import io.vavr.collection.List;
import io.vavr.control.Option;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

public class OkTest {
  private static final CarRepository repository;
  static {
    repository = new CarRepository();
  }

  @Test
  void iterate() {
    assertThat(
      Array.of(1, 2, 3)
        .map(i -> i * 10)
        .peek(System.out::println)
    )
      .containsExactly(10, 20 ,30);
  }

  @Test
  void join() {
    assertThat(
      List.of("Haskell", "Scala", "Java")
        .mkString(", ")
    )
      .isEqualTo("Haskell, Scala, Java");
  }

  @Test
  void iteratingWithIndex() {
    assertThat(
      List.of("Haskell", "Scala", "Java")
        .zipWithIndex((arg, index) -> index + ". " + arg)
    )
      .containsExactly("0. Haskell", "1. Scala", "2. Java");
  }

  @Test
  void oldWay() {
    Car car = repository.findCarById("1A9 4321");
    assertThat(car)
      .isNotNull();

    Car nullCar = repository.findCarById("M 432 KT");
    assertThat(nullCar)
      .isNull();
  }

  void newWay() {
    final var optional = repository.findCarByIdWithOptional("5C9 9984");
    final var option = Option.ofOptional(repository.findCarByIdWithOptional("5C9 9984"));

    // optional.get();
    // option.get();
    //
    // optional.isPresent();
    // option.isDefined();
    //
    // optional.orElseGet();
    // option.orElse();
    //
    // option.
    //
    // Optional.of()
    // Optional.ofNullable()
    // Option.of()
  }
}

@Getter
@RequiredArgsConstructor
class Car {
  private final String name;
  private final String id;
  private final String color;

  @Override
  public String toString() {
    return "Car "+name+" with license id "+id+" and of color "+color;
  }
}

class CarRepository {

  private java.util.List<Car> cars;

  public CarRepository(){
    getSomeCars();
  }

  Car findCarById(String id){
    for (Car car: cars){
      if (car.getId().equalsIgnoreCase(id)){
        return car;
      }
    }
    return null;
  }

  Optional<Car> findCarByIdWithOptional(String id){
    return cars.stream().filter(car->car.getId().equalsIgnoreCase(id)).findFirst();
  }

  private void getSomeCars(){
    cars = new java.util.ArrayList<>();
    cars.add(new Car("tesla", "1A9 4321", "red"));
    cars.add(new Car("volkswagen", "2B1 1292", "blue"));
    cars.add(new Car("skoda", "5C9 9984", "green"));
    cars.add(new Car("audi", "8E4 4321", "silver"));
    cars.add(new Car("mercedes", "3B4 5555", "black"));
    cars.add(new Car("seat", "6U5 3123", "white"));
  }
}
